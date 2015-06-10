package com.jking31cs.triangle;

import com.jking31cs.vector.Vector3D;

/**
 * Color object.
 */
public class Color extends Vector3D {

    public Color(Vector3D v) {
        this(v.x, v.y, v.z);
    }

    public Color(float x, float y, float z) {
        super(x, y, z);
    }

    public Color colorMultiply(Color c) {
        return new Color(x*c.x, y*c.y, z*c.z);
    }
}
