package com.jking31cs.vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jking31cs on 5/19/15.
 */
public class Vector2DTest {

    @Test
    public void testCreation() {
        Vector2D v = new Vector2D(5,8);
        assertEquals("X should be 5", 5f, v.x, .0001);
        assertEquals("Y should be 8", 8f, v.y, .0001);
    }

    @Test
    public void testAddition() {
        Vector2D a =  new Vector2D(2,2);
        Vector2D b =  new Vector2D(3,5);
        Vector2D a_plus_b = a.add(b);

        assertEquals("X should be 5", 5f, a_plus_b.x, .0001);
        assertEquals("Y should be 7", 7f, a_plus_b.y, .0001);
    }

    @Test
    public void testSubtraction() {
        Vector2D a = new Vector2D(8,6);
        Vector2D b = new Vector2D(3,2);

        Vector2D a_sub_b = a.sub(b);

        assertEquals("X should be 5", 5f, a_sub_b.x, .0001);
        assertEquals("Y should be 4", 4f, a_sub_b.y, .0001);
    }

    @Test
    public void testDotProduct() {
        Vector2D a = new Vector2D(8,6);
        Vector2D b = new Vector2D(3,2);

        float a_dot_b = a.dot(b);

        assertEquals("a dot b should equal 36", 36f, a_dot_b, .0001);
    }
}
