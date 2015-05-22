package com.jking31cs.example;

import java.awt.event.KeyEvent;

import com.jking31cs.matrix.Matrix;
import com.jking31cs.projection.CameraTransformMatrixGenerator;
import com.jking31cs.projection.ProjectionMatrixGenerator;
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

    @Override
    public void setup() {
        size(300,300);
        cameraLoc = new Vector3D(5,5,10);
        lookAtPoint = new Vector3D(0,0,15);
        matrix = ProjectionMatrixGenerator.getProjectionMatrix((float) Math.PI / 3, 5, 20, 1);
    }

    @Override
    public void draw() {
        background(255);
        Vector3D[] points = new Vector3D[] {
            new Vector3D(5, 5, 21),
            new Vector3D(39, 26, 27),
            new Vector3D(10, -40, 15),
            new Vector3D(5, 5, 21)
        };

        drawLines(points);
    }

    private void drawLines(Vector3D[] points) {
        for (int i = 1; i < points.length; i++) {
            Vector3D p1 = points[i-1];
            Vector2D pt1 = transform(p1);
            Vector3D p2 = points[i];
            Vector2D pt2 = transform(p2);

            drawLine(pt1, pt2);
        }
    }

    private void drawLine(Vector2D pt1, Vector2D pt2) {
        float midX = width/2;
        float midY = height/2;

        line(midX-pt1.x, midY-pt1.y, midX-pt2.x, midY-pt2.y);
    }

    private Vector2D transform(Vector3D p) {
        //First rotate it so it's in the correct location by the camera perspective.
        VectorND p_4 = new VectorND(p.x, p.y, p.z, 1);
        Matrix cam_matrix = CameraTransformMatrixGenerator.generate(cameraLoc, lookAtPoint);
        System.out.println(lookAtPoint);
        p_4 = cam_matrix.mul(p_4);

        //Now project it
        p_4 = matrix.mul(p_4);

        return new Vector2D(p_4.values[0], p_4.values[1]);

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyChar() == 'a') {
            cameraLoc = cameraLoc.sub(new Vector3D(1,0,0));
        } else if (event.getKeyChar() == 'd') {
            cameraLoc = cameraLoc.add(new Vector3D(1,0,0));
        } else if (event.getKeyChar() == 's') {
            cameraLoc = cameraLoc.add(new Vector3D(0,1,0));
        } else if (event.getKeyChar() == 'w') {
            cameraLoc = cameraLoc.sub(new Vector3D(0,1,0));
        }
    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        float diff_z = e.getY() - pmouseY;
//        float diff_x = e.getX() - pmouseX;
//
//        lookAtPoint = lookAtPoint.add(new Vector3D(diff_x*.001f, diff_z*.001f, 0));
//    }
}
