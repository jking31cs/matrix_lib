package com.jking31cs.projection;

import com.jking31cs.matrix.Matrix;
import com.jking31cs.vector.Vector3D;
/**
 * Created by jking31 on 5/22/15.
 */
public class CameraTransformMatrixGenerator {

    public static Matrix generate(Vector3D cameraLoc, Vector3D lookAtPoint) {

        Vector3D vz = cameraLoc.sub(lookAtPoint).norm();
        Vector3D vx = new Vector3D(0,1,0).cross(vz).norm();
        Vector3D vy = vz.cross(vx);

        return new Matrix(new float[][] {
            new float[] {vx.x, vy.x, vz.x, cameraLoc.x},
            new float[] {vx.y, vy.y, vz.y, cameraLoc.y},
            new float[] {vx.z, vy.z, vz.z, cameraLoc.z},
            new float[] {0, 0, 0, 1},
        });
    }

    private static float cast(double d) {
        return (float) d;
    }
}
