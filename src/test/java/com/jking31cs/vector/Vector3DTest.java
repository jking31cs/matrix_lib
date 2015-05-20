package com.jking31cs.vector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jking31cs on 5/19/15.
 */
public class Vector3DTest {

    @Test
    public void testCreation() {
        Vector3D v = new Vector3D(5,8,3);
        assertEquals("X should be 5", 5f, v.x, .0001);
        assertEquals("Y should be 8", 8f, v.y, .0001);
        assertEquals("Z should be 3", 3f, v.z, .0001);
    }

    @Test
    public void testAddition() {
        Vector3D a =  new Vector3D(2,2,2);
        Vector3D b =  new Vector3D(3,5,7);
        Vector3D a_plus_b = a.add(b);

        assertEquals("X should be 5", 5f, a_plus_b.x, .0001);
        assertEquals("Y should be 7", 7f, a_plus_b.y, .0001);
        assertEquals("Z should be 9", 9f, a_plus_b.z, .0001);
    }

    @Test
    public void testSubtraction() {
        Vector3D a = new Vector3D(8,6,6);
        Vector3D b = new Vector3D(3,2,1);

        Vector3D a_sub_b = a.sub(b);

        assertEquals("X should be 5", 5f, a_sub_b.x, .0001);
        assertEquals("Y should be 4", 4f, a_sub_b.y, .0001);
        assertEquals("Z should be 5", 5f, a_sub_b.z, .0001);
    }

    @Test
    public void testDotProduct() {
        Vector3D a = new Vector3D(8,6,1);
        Vector3D b = new Vector3D(3,2,5);

        float a_dot_b = a.dot(b);

        assertEquals("a dot b should equal 41", 41f, a_dot_b, .0001);
    }

    @Test
    public void testCrossProduct() {
        Vector3D a = new Vector3D(1,0,0);
        Vector3D b = new Vector3D(0,1,0);

        Vector3D a_X_b = a.cross(b);

        assertEquals("X should be 0", 0f, a_X_b.x, .0001);
        assertEquals("Y should be 0", 0f, a_X_b.y, .0001);
        assertEquals("Z should be 1", 1f, a_X_b.z, .0001);
    }

    @Test
    public void testCrossProduct2() {
        Vector3D a = new Vector3D(1,0,0);
        Vector3D b = new Vector3D(0,0,1);

        Vector3D a_X_b = a.cross(b);

        assertEquals("X should be 0", 0f, a_X_b.x, .0001);
        assertEquals("Y should be -1", -1f, a_X_b.y, .0001);
        assertEquals("Z should be 0", 0f, a_X_b.z, .0001);
    }
}
