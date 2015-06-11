package com.jking31cs.example;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jking31cs.light.PointLight;
import com.jking31cs.matrix.Matrix;
import com.jking31cs.projection.CameraTransformMatrixGenerator;
import com.jking31cs.projection.ProjectionMatrixGenerator;
import com.jking31cs.shape.Shape;
import com.jking31cs.triangle.Color;
import com.jking31cs.triangle.Triangle;
import com.jking31cs.vector.Vector2D;
import com.jking31cs.vector.Vector3D;
import com.jking31cs.vector.VectorND;
import processing.core.PApplet;

/**
 * Created by jking31 on 5/22/15.
 */
public class MyApplet extends PApplet {


    Vector3D cameraLoc;
    Vector3D lookAtPoint;

    Matrix matrix;

    List<Triangle> triangles;
    PointLight light;

    @Override
    public void setup() {
        size(500,500);
        cameraLoc = new Vector3D(0,0,50);
        lookAtPoint = new Vector3D(0,0,0);
        matrix = ProjectionMatrixGenerator.getProjectionMatrix((float) Math.PI/8, 20, 50, 1);

        triangles = new ArrayList<>();
        light = new PointLight(new Vector3D(100,100,100), new Color(1f, 1f, 1f), .5f, 3f);

        selectInput("Open file", "readFile");
    }

    public void readFile(File file) throws IOException {
        Shape shape = Shape.readFromFile(file);
        triangles = shape.triangleList;

    }

    @Override
    public void draw() {
        background(0);
        final Matrix cam_matrix = CameraTransformMatrixGenerator.generate(cameraLoc, lookAtPoint);
        triangles.sort(new Comparator<Triangle>() {
            @Override
            public int compare(Triangle o1, Triangle o2) {
                Float z1 = transformPoint(o1.getCentroid()).values[2];
                Float z2 = transformPoint(o2.getCentroid()).values[2];
                return z1.compareTo(z2);
            }

            private VectorND transformPoint(Vector3D p) {
                VectorND p_4 = new VectorND(p.x, p.y, p.z, 1);
                return cam_matrix.mul(p_4);
            }
        });
        Collections.reverse(triangles);
        drawTriangles();
    }

    private void drawTriangles() {
        float midX = width/2;
        float midY = height/2;
        for (Triangle t : triangles) {
            Vector2D p1 = transform(t.p1);
            Vector2D p2 = transform(t.p2);
            Vector2D p3 = transform(t.p3);

            Color c = calcColor(t);
           // System.out.println("Color: " + c.x + " " + c.y + " " + c.z);
            fill(c.x * 255f, c.y * 255f, c.z * 255f);
            stroke(c.x * 255f, c.y * 255f, c.z * 255f);
            triangle(midX - p1.x, midY - p1.y, midX - p2.x, midY - p2.y, midX - p3.x, midY - p3.y);
        }
    }

    private Vector2D transform(Vector3D p) {
        //First rotate it so it's in the correct location by the camera perspective.
        VectorND p_4 = new VectorND(p.x, p.y, p.z, 1);
        Matrix cam_matrix = CameraTransformMatrixGenerator.generate(cameraLoc, lookAtPoint);

        //Now project it
        p_4 = matrix.mul(cam_matrix).mul(p_4);
        if (p_4.values[3] == p.z) {
            System.out.println("Things are working");
        }
        return new Vector2D(p_4.values[0], p_4.values[1]);

    }

    private Color calcColor(Triangle t) {
        Vector3D L = light.position.sub(t.getCentroid()).norm();
        Vector3D n = t.getNormal();
        Color diffuse = new Color(t.m_diffuse.mul(Math.max(L.dot(n), 0)));
        Color ambient = t.m_ambient;

        Vector3D E = cameraLoc.sub(t.getCentroid()).norm();
        Vector3D H = L.add(E).mul(1f / (L.add(E).mag()));

        Color spec = new Color(t.m_spec.mul((float) Math.pow(Math.max(H.dot(n), 0), light.spec)));
        //System.out.println("Diffuse: " + diffuse);
        return new Color(ambient.colorMultiply(light.lightColor).add(diffuse.colorMultiply(getDiffuseLightColor())).add(spec));
    }

    private Color getDiffuseLightColor() {
        return new Color(new Vector3D(1, 1, 1).mul(light.strength));
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == 'a') {
            cameraLoc = CameraTransformMatrixGenerator.moveCameraAroundLookAt(cameraLoc, lookAtPoint, CameraTransformMatrixGenerator.Axis.Y, PI / 12);
        } else if (event.getKeyChar() == 'd') {
            cameraLoc = CameraTransformMatrixGenerator.moveCameraAroundLookAt(cameraLoc, lookAtPoint, CameraTransformMatrixGenerator.Axis.Y, -PI / 12);
        } else if (event.getKeyChar() == 's') {
            cameraLoc = CameraTransformMatrixGenerator.moveCameraAroundLookAt(cameraLoc, lookAtPoint, CameraTransformMatrixGenerator.Axis.X, PI / 12);
        } else if (event.getKeyChar() == 'w') {
            cameraLoc = CameraTransformMatrixGenerator.moveCameraAroundLookAt(cameraLoc, lookAtPoint, CameraTransformMatrixGenerator.Axis.X, -PI / 12);
        }
        System.out.println("Camera Loc: " + cameraLoc);
        System.out.println(lookAtPoint);
    }

    public static void main(String[] args) {
        PApplet.main(MyApplet.class.getName(), args);
    }



}
