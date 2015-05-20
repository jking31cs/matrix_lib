package com.jking31cs.vector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jking31 on 5/20/15.
 */
public class VectorNDTest {

    @Test
    public void testCreation() {
        VectorND v = new VectorND(1f,2f,3f,0f);

        assertEquals(4, v.values.length);
        assertEquals(1, v.values[0], .0001);
        assertEquals(2, v.values[1], .0001);
        assertEquals(3, v.values[2], .0001);
        assertEquals(0, v.values[3], .0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFails() {
        VectorND v = new VectorND(1f,2f,3f,0f);
        VectorND u = new VectorND(1f,2f,0f);
        v.add(u);
    }

    @Test
    public void testAdd() {
        VectorND v = new VectorND(1f,2f,3f,0f);
        VectorND u = new VectorND(1f,2f,0f,4f);
        VectorND result = v.add(u);

        assertEquals(4, result.values.length);
        assertEquals(2, result.values[0], .0001);
        assertEquals(4, result.values[1], .0001);
        assertEquals(3, result.values[2], .0001);
        assertEquals(4, result.values[3], .0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubFails() {
        VectorND v = new VectorND(1f,2f,3f,0f);
        VectorND u = new VectorND(1f,2f,0f);
        v.sub(u);
    }

    @Test
    public void testSub() {
        VectorND v = new VectorND(5f,3f,3f,10f);
        VectorND u = new VectorND(1f,2f,0f,4f);
        VectorND result = v.sub(u);

        assertEquals(4, result.values.length);
        assertEquals(4, result.values[0], .0001);
        assertEquals(1, result.values[1], .0001);
        assertEquals(3, result.values[2], .0001);
        assertEquals(6, result.values[3], .0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotFails() {
        VectorND v = new VectorND(1f,2f,3f,0f);
        VectorND u = new VectorND(1f,2f,0f);
        v.dot(u);
    }

    @Test
    public void testDot() {
        VectorND v = new VectorND(5f,3f,3f,10f);
        VectorND u = new VectorND(1f,2f,0f,4f);
        float result = v.dot(u);

        assertEquals(51, result, .0001);
    }
}
