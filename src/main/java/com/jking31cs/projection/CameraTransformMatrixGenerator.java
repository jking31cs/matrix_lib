package com.jking31cs.projection;

import com.jking31cs.matrix.Matrix;
import com.jking31cs.vector.Vector3D;
import com.jking31cs.vector.VectorND;

import static java.lang.Math.*;
/**
 * Created by jking31 on 5/22/15.
 */
public class CameraTransformMatrixGenerator {

    public enum Axis {
        X,Y,Z
    }

    public static Matrix generate(Vector3D cameraLoc, Vector3D lookAtPoint) {

        Vector3D vz = cameraLoc.sub(lookAtPoint).norm();
        Vector3D vx = new Vector3D(0,1,0).cross(vz).norm();
        Vector3D vy = vz.cross(vx);

        return (new Matrix(new float[][] {
            new float[] {vx.x, vy.x, vz.x, 0},
            new float[] {vx.y, vy.y, vz.y, 0},
            new float[] {vx.z, vy.z, vz.z, 0},
            new float[] {vx.dot(cameraLoc), vy.dot(cameraLoc), vz.dot(cameraLoc), 1},
        })).transpose();
    }

    public static Vector3D moveCameraAroundLookAt(
            Vector3D c,
            Vector3D a,
            Axis axis,
            float angle
    ) {
        //First, move camera such that lookAtPoint is at origin

        Matrix moveToOrigin = new Matrix(new float[][] {
            new float[] {1, 0, 0, -a.x},
            new float[] {0, 1, 0, -a.y},
            new float[] {0, 0, 1, -a.z},
            new float[] {0, 0, 0, 1},
        });

        //Then rotate around proper axis
        Matrix rotate = null;
        switch (axis) {
            case X:
                rotate = new Matrix(new float[][] {
                    new float[] {1,0,0,0},
                    new float[] {0, cast(cos(angle)), -cast(sin(angle)), 0},
                    new float[] {0, cast(sin(angle)), cast(cos(angle)), 0},
                    new float[] {0, 0, 0, 1}
                });
                break;
            case Y:
                rotate = new Matrix(new float[][] {
                    new float[] {cast(cos(angle)),0,-cast(sin(angle)),0},
                    new float[] {0, 1, 0, 0},
                    new float[] {cast(sin(angle)), 0, cast(cos(angle)), 0},
                    new float[] {0, 0, 0, 1}
                });
                break;
            case Z:
                rotate = new Matrix(new float[][] {
                    new float[] {cast(cos(angle)), -cast(sin(angle)), 0, 0},
                    new float[] {cast(sin(angle)), cast(cos(angle)), 0, 0},
                    new float[] {0, 0, 1, 0},
                    new float[] {0, 0, 0, 1}
                });
                break;
        }

        //Then move the camera back to its initial location
        Matrix moveBack = new Matrix(new float[][] {
            new float[] {1, 0, 0, a.x},
            new float[] {0, 1, 0, a.y},
            new float[] {0, 0, 1, a.z},
            new float[] {0, 0, 0, 1},
        });

        VectorND v = moveBack.mul(rotate).mul(moveToOrigin).mul(new VectorND(c.x, c.y, c.z, 1));
        return new Vector3D(v.values[0],v.values[1],v.values[2]);
    }

    private static float cast(double d) {
        return (float) d;
    }
}
