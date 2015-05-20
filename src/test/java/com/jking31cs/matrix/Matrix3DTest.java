package com.jking31cs.matrix;

import com.jking31cs.vector.Vector3D;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by jking31cs on 5/19/15.
 */
public class Matrix3DTest {

    @Test
    public void testCreation() {
        Matrix3D i = Matrix3D.I;

        assertEquals(i.a, 1f, .0001);
        assertEquals(i.b, 0f, .0001);
        assertEquals(i.c, 0f, .0001);
        assertEquals(i.d, 0f, .0001);
        assertEquals(i.e, 1f, .0001);
        assertEquals(i.f, 0f, .0001);
        assertEquals(i.g, 0f, .0001);
        assertEquals(i.h, 0f, .0001);
        assertEquals(i.i, 1f, .0001);
    }

    @Test
    public void testMatrixMul() {
        Matrix3D ace = new Matrix3D(
                1,1,1,
                1,1,1,
                1,1,1
        );
        Matrix3D something = new Matrix3D(
                2,3,2,
                1,2,1,
                3,4,3
        );

        Matrix3D result = ace.mul(something);

        assertEquals(result.a, 6f, .0001);
        assertEquals(result.b, 9f, .0001);
        assertEquals(result.c, 6f, .0001);
        assertEquals(result.d, 6f, .0001);
        assertEquals(result.e, 9f, .0001);
        assertEquals(result.f, 6f, .0001);
        assertEquals(result.g, 6f, .0001);
        assertEquals(result.h, 9f, .0001);
        assertEquals(result.i, 6f, .0001);
    }

    @Test
    public void testMatrixVectorMul() {
        Matrix3D something = new Matrix3D(
                2,3,2,
                1,2,1,
                3,4,3
        );
        Vector3D aces = new Vector3D(1,1,1);

        Vector3D result = something.mul(aces);

        assertEquals(7f, result.x, .0001);
        assertEquals(4f, result.y, .0001);
        assertEquals(10f, result.z, .0001);
    }
}
