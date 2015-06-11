package com.jking31cs.projection;

import com.jking31cs.matrix.Matrix;

import static java.lang.Math.tan;

/**
 * Created by jking31 on 5/21/15.
 */
public class ProjectionMatrixGenerator {
    private float fov, nearPlane, farPlane, aspectRatio;

    public static Matrix getProjectionMatrix(float fov, float nearPlane, float farPlane, float aspectRatio) {
        return (new Matrix(new float[][] {
            new float[]{(float) ((1f/ aspectRatio)*(1/tan(fov/2))), 0,0,0},
            new float[]{0, (float) (1/tan(fov/2)), 0, 0},
            new float[]{0, 0, farPlane /(nearPlane - farPlane), -1},
            new float[]{0,0,(farPlane*nearPlane) / (nearPlane - farPlane),0}
        })).transpose();
    }
}
