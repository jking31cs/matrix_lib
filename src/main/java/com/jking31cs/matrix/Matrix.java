package com.jking31cs.matrix;

import java.util.Objects;

import com.jking31cs.vector.VectorND;

/**
 * Represents a matrix of a dynamic size.
 */
public class Matrix {
    public final float[][] rows;

    public Matrix(float[][] rows) {
        int length = rows[0].length;
        for (float[] row : rows) {
            if (row.length != length) {
                throw new IllegalArgumentException("All rows must be the same length.");
            }
        }
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        return Objects.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (float[] row : rows) {
            sb.append("{ ");
            for (float f : row) {
                sb.append(f + " ");
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

    public Matrix transpose() {
        float[][] cols = new float[rows[0].length][rows.length];
        for (int i = 0; i < rows.length; i++) {
            float[] row = rows[i];
            for (int j = 0; j < row.length; j++) {
                cols[j][i] = row[j];
            }
        }
        return new Matrix(cols);
    }

    public Matrix mul(float s) {
        float[][] newValues = new float[rows.length][rows[0].length];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length; j++) {
                newValues[i][j] = rows[i][j] * s;
            }
        }
        return new Matrix(newValues);
    }

    public Matrix mul(Matrix m) {
        //Check if valid
        int expectedColSize = rows[0].length;
        if (m.rows.length != expectedColSize) {
            throw new IllegalArgumentException("This matrix cannot be multiplied by the given argument.");
        }
        //Use transpose since it's easy.
        Matrix m_trans = m.transpose();
        float[][] newValues = new float[rows.length][m_trans.rows.length];
        for (int i = 0; i < rows.length; i++) {
            float[] row = rows[i];
            for (int j = 0; j < m_trans.rows.length; j++) {
                float[] col = m_trans.rows[j];
                float sum = 0;
                for (int k = 0; k < row.length; k++) {
                    sum += row[k]*col[k];
                }
                newValues[i][j] = sum;
            }
        }
        return new Matrix(newValues);

    }

    public VectorND mul(VectorND v) {
        if (v.values.length != rows[0].length) {
            throw new IllegalArgumentException("This vector is not the correct size to be multiplied by this matrix.");
        }
        float[] newValues = new float[rows.length];
        for (int i = 0; i < rows.length; i++) {
            float sum = 0;
            float[] row = rows[i];
            for (int k = 0; k < row.length; k++) {
                sum += row[k]*v.values[k];
            }
            newValues[i] = sum;
        }
        return new VectorND(newValues);
    }
}
