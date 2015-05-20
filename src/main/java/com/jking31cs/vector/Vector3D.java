package com.jking31cs.vector;

import java.util.Objects;

/**
 * Three Dimensional Vector.
 */
public class Vector3D {
    
    public final float x,y,z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return Objects.equals(x, vector3D.x) &&
                Objects.equals(y, vector3D.y) &&
                Objects.equals(z, vector3D.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    /**
     * Adds the given vector to this vector.
     *
     * {2,3,1} + {3,5,2} = {5,8,3}
     * @param b The vector to add to this
     * @return the sum of the two vectors.
     */
    public Vector3D add(Vector3D b) {
        return new Vector3D(x+b.x, y+b.y, z+b.z);
    }

    /**
     * Subtracts the given vector from this vector.
     *
     * {8,6,5} - {3,2,2} = {5,4,3}
     * @param b The vector to subtract from this.
     * @return the result.
     */
    public Vector3D sub(Vector3D b) {
        return new Vector3D(x-b.x, y-b.y, z-b.z);
    }

    /**
     * Returns the dot product of this vector and the given vector.
     *
     * {8,6,1} • {3,2,5} = 24+12+5 = 41
     * @param b The vector to dot
     * @return the result
     */
    public float dot(Vector3D b) {
        return x*b.x + y*b.y + z*b.z;
    }

    /**
     * Returns the cross product between this and the given vector.
     * @param b The vector to cross
     * @return The result.
     */
    public Vector3D cross(Vector3D b) {
        return new Vector3D(
                y*b.z-z*b.y,
                b.x*z-b.z*x,
                x*b.y-b.x*y
        );
    }
}
