package com.jking31cs.vector;

import java.util.Objects;

/**
 * Created by jking31cs on 5/20/15.
 */
public class VectorND {

    public final float[] values;

    public VectorND(float[] values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VectorND vectorND = (VectorND) o;
        return Objects.equals(values, vectorND.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    public VectorND add(VectorND v) {
        if (v.values.length != values.length) {
            throw new IllegalArgumentException("Sizes of Vectors must be equal");
        }
        float[] newValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = v.values[i] + values[i];
        }
        return new VectorND(newValues);
    }

    public VectorND sub(VectorND v) {
        if (v.values.length != values.length) {
            throw new IllegalArgumentException("Sizes of Vectors must be equal");
        }
        float[] newValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i] - v.values[i];
        }
        return new VectorND(newValues);
    }

    public float dot(VectorND v) {
        if (v.values.length != values.length) {
            throw new IllegalArgumentException("Sizes of Vectors must be equal");
        }
        float result = 0;
        for (int i = 0; i < values.length; i++) {
            result += values[i] * v.values[i];
        }
        return result;
    }
}
