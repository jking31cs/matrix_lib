package com.jking31cs.example;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import com.jking31cs.matrix.Matrix;
import com.jking31cs.projection.CameraTransformMatrixGenerator;
import com.jking31cs.projection.ProjectionMatrixGenerator;
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

    @Override
    public void setup() {
        size(500,500);
        cameraLoc = new Vector3D(0,0,0);
        lookAtPoint = new Vector3D(0,0,15);
        matrix = ProjectionMatrixGenerator.getProjectionMatrix((float) Math.PI / 3, 20, 50, 1);

        triangles = Arrays.asList(
            new Triangle(
                new Vector3D(250,25,2),
                new Vector3D(25,400,3),
                new Vector3D(-25,-300,2),
                new Color(1,1,1),
                new Color(255,0,0)
            )
        );
    }

    @Override
    public void draw() {
        background(255);
        drawTriangles();
    }

    private void drawTriangles() {
        float midX = width/2;
        float midY = height/2;
        for (Triangle t : triangles) {
            Vector2D p1 = transform(t.p1);
            Vector2D p2 = transform(t.p2);
            Vector2D p3 = transform(t.p3);

            Color c = t.light.colorMultiply(t.material);
            fill(c.x, c.y, c.z);
            triangle(midX - p1.x, midY - p1.y, midX - p2.x, midY - p2.y, midX - p3.x, midY - p3.y);
        }
    }

    private Vector2D transform(Vector3D p) {
        //First rotate it so it's in the correct location by the camera perspective.
        VectorND p_4 = new VectorND(p.x, p.y, p.z, 1);
        Matrix cam_matrix = CameraTransformMatrixGenerator.generate(cameraLoc, lookAtPoint);
        p_4 = cam_matrix.mul(p_4);

        //Now project it
        p_4 = matrix.mul(p_4);

        return new Vector2D(p_4.values[0]/p.z, p_4.values[1]/p.z);

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


}
