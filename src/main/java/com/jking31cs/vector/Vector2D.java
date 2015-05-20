package com.jking31cs.vector;

import java.util.Objects;

/**
 * Meant to represent a 2 dimensional Vector and its operations.  This class is immutable, so any operations should
 * return a new Vector2D object.
 */
public class Vector2D {

    public final float x;
    public final float y;


    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return Objects.equals(x, vector2D.x) &&
                Objects.equals(y, vector2D.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Adds the given vector to this vector.
     *
     * {2,3} + {3,5} = {5,8}
     * @param b The vector to add to this
     * @return the sum of the two vectors.
     */
    public Vector2D add(Vector2D b) {
        return new Vector2D(x+b.x, y+b.y);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * {8,6} - {3,2} = {5,4}
     * @param b The vector to subtract from this.
     * @return the result.
     */
    public Vector2D sub(Vector2D b) {
        return new Vector2D(x-b.x, y-b.y);
    }

    /**
     * Returns the dot product of this vector and the given vector.
     *
     * {8,6} • {3,2} = 24+12 = 36
     * @param b The vector to dot
     * @return the result
     */
    public float dot(Vector2D b) {
        return x*b.x + y*b.y;
    }
}
