package com.jking31cs.triangle;

import com.jking31cs.vector.Vector3D;
import processing.core.PApplet;

/**
 * Created by jking31cs on 5/30/15.
 */
public class Triangle {
    public Vector3D p1, p2, p3;
    public Color material, light;

    public Triangle(
        Vector3D p1,
        Vector3D p2,
        Vector3D p3,
        Color material,
        Color light
    ) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.material = material;
        this.light = light;
    }
}
