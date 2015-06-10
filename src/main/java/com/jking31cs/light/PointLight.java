package com.jking31cs.light;

import com.jking31cs.triangle.Color;
import com.jking31cs.vector.Vector3D;

import java.util.Objects;

/**
 * Created by jking31cs on 6/5/15.
 */
public class PointLight {

    public final Vector3D position;
    public final Color lightColor;
    public final float strength;
    public final float spec;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLight that = (PointLight) o;
        return Objects.equals(strength, that.strength) &&
            Objects.equals(position, that.position) &&
            Objects.equals(lightColor, that.lightColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, lightColor, strength);
    }

    public PointLight(Vector3D position, Color lightColor, float strength, float spec) {
        this.position = position;
        this.lightColor = lightColor;

        this.strength = strength;
        this.spec = spec;
    }

}
