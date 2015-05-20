package com.jking31cs.matrix;

import com.jking31cs.vector.VectorND;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jking31 on 5/20/15.
 */
public class MatrixTest {

    @Test
    public void testCreation() {
        Matrix m = new Matrix(new float[][]{
            new float[]{1f, 2f, 3f},
            new float[]{2f, 3f, 4f}
        });

        assertEquals(2, m.rows.length);

        assertEquals(1, m.rows[0][0], .0001);
        assertEquals(2, m.rows[0][1], .0001);
        assertEquals(3, m.rows[0][2], .0001);
        assertEquals(2, m.rows[1][0], .0001);
        assertEquals(3, m.rows[1][1], .0001);
        assertEquals(4, m.rows[1][2], .0001);
    }

    @Test
    public void testTranspose() {
        Matrix m = new Matrix(new float[][]{
            new float[]{1f, 2f, 3f},
            new float[]{2f, 3f, 4f}
        });

        Matrix t = m.transpose();

        assertEquals(3, t.rows.length);

        assertEquals(1, t.rows[0][0], .0001);
        assertEquals(2, t.rows[1][0], .0001);
        assertEquals(3, t.rows[2][0], .0001);
        assertEquals(2, t.rows[0][1], .0001);
        assertEquals(3, t.rows[1][1], .0001);
        assertEquals(4, t.rows[2][1], .0001);
    }

    @Test
    public void testMulScale() {
        Matrix m = new Matrix(new float[][]{
            new float[]{1f, 2f, 3f},
            new float[]{2f, 3f, 4f}
        });

        Matrix t = m.mul(2f);

        assertEquals(2, t.rows.length);

        assertEquals(2, t.rows[0][0], .0001);
        assertEquals(4, t.rows[0][1], .0001);
        assertEquals(6, t.rows[0][2], .0001);
        assertEquals(4, t.rows[1][0], .0001);
        assertEquals(6, t.rows[1][1], .0001);
        assertEquals(8, t.rows[1][2], .0001);
    }

    @Test
    public void testMulMatrix() {
        Matrix m = new Matrix(new float[][]{
            new float[]{1f, 2f, 3f},
            new float[]{2f, 3f, 4f}
        });
        Matrix m2 = new Matrix(new float[][]{
            new float[]{1f, 2f},
            new float[]{2f, 3f},
            new float[]{1f, 1f}
        });

        Matrix t = m.mul(m2);

        assertEquals(2, t.rows.length);
        assertEquals(2, t.rows[0].length);

        assertEquals(8, t.rows[0][0], .0001);
        assertEquals(12, t.rows[1][0], .0001);
        assertEquals(11, t.rows[0][1], .0001);
        assertEquals(17, t.rows[1][1], .0001);
        System.out.println(t);
    }

    @Test
    public void testMulMatrix2() {
        Matrix m = new Matrix(new float[][]{
            new float[]{1f, 2f, 3f},
            new float[]{2f, 3f, 4f},
            new float[]{1f, 1f, 1f}
        });
        Matrix m2 = new Matrix(new float[][]{
            new float[]{1f, 2f},
            new float[]{2f, 3f},
            new float[]{1f, 1f}
        });

        Matrix t = m.mul(m2);

        assertEquals(3, t.rows.length);
        assertEquals(2, t.rows[0].length);

        assertEquals(8, t.rows[0][0], .0001);
        assertEquals(11, t.rows[0][1], .0001);
        assertEquals(12, t.rows[1][0], .0001);
        assertEquals(17, t.rows[1][1], .0001);
        assertEquals(4, t.rows[2][0], .0001);
        assertEquals(6, t.rows[2][1], .0001);

        System.out.println(t);
    }

    @Test
    public void testMulVector() {
        Matrix m = new Matrix(new float[][]{
            new float[]{1f, 2f, 3f, 1f},
            new float[]{2f, 3f, 4f, 1f},
            new float[]{1f, 1f, 1f, 1f},
            new float[]{1f, 2f, 2f, 1f}
        });
        VectorND v = new VectorND(1f,2f,3f,4f);

        VectorND result = m.mul(v);

        assertEquals(4, result.values.length);

        assertEquals(18, result.values[0], .0001);
        assertEquals(24, result.values[1], .0001);
        assertEquals(10, result.values[2], .0001);
        assertEquals(15, result.values[3], .0001);
    }


}
