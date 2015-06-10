package com.jking31cs.triangle;

import com.jking31cs.vector.Vector3D;

import java.util.Objects;

/**
 * Created by jking31cs on 5/30/15.
 */
public class Triangle {
    public Vector3D p1, p2, p3;
    public Color m_ambient, m_diffuse;

    public Triangle(
        Vector3D p1,
        Vector3D p2,
        Vector3D p3,
        Color m_ambient,
        Color m_diffuse
    ) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.m_ambient = m_ambient;
        this.m_diffuse = m_diffuse;
    }

    public Vector3D getCentroid() {
        return p1.add(p2).add(p3).mul(1f/3f);
    }

    public Vector3D getNormal() {
        Vector3D A = p1.sub(p2);
        Vector3D B = p3.sub(p1);
        return A.cross(B).norm();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(p1, triangle.p1) &&
            Objects.equals(p2, triangle.p2) &&
            Objects.equals(p3, triangle.p3) &&
            Objects.equals(m_ambient, triangle.m_ambient) &&
            Objects.equals(m_diffuse, triangle.m_diffuse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, p3, m_ambient, m_diffuse);
    }
}
