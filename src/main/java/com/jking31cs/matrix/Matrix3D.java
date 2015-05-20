package com.jking31cs.matrix;

import com.jking31cs.vector.Vector3D;

import java.util.Objects;

/**
 * 3D Matrix along with basic operations.
 */
public class Matrix3D {
    public static final Matrix3D I = new Matrix3D(
            1,0,0,
            0,1,0,
            0,0,1
    );

    public final float
            a, b, c,
            d, e, f,
            g, h, i;


    public Matrix3D(
            float a, float b, float c,
            float d, float e, float f,
            float g, float h, float i) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix3D matrix3D = (Matrix3D) o;
        return Objects.equals(a, matrix3D.a) &&
                Objects.equals(b, matrix3D.b) &&
                Objects.equals(c, matrix3D.c) &&
                Objects.equals(d, matrix3D.d) &&
                Objects.equals(e, matrix3D.e) &&
                Objects.equals(f, matrix3D.f) &&
                Objects.equals(g, matrix3D.g) &&
                Objects.equals(h, matrix3D.h) &&
                Objects.equals(i, matrix3D.i);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d, e, f, g, h, i);
    }

    public Vector3D mul(Vector3D v) {
        return new Vector3D(
                a*v.x + b*v.y + c*v.z,
                d*v.x + e*v.y + f*v.z,
                g*v.x + h*v.y + i*v.z
        );
    }

    public Matrix3D add(Matrix3D m) {
        return new Matrix3D(
                m.a + a, m.b + b, m.c + c,
                m.d + d, m.e + e, m.f + f,
                m.g + g, m.h + h, m.i + i
        );
    }

    public Matrix3D mul(float s) {
        return new Matrix3D(
                s*a,s*b,s*c,
                s*d,s*e,s*f,
                s*g,s*h,s*i
        );
    }

    public Matrix3D mul(Matrix3D m) {
        return new Matrix3D(
                a*m.a + b*m.d + c*m.g, a*m.b + b*m.e + c*m.h, a*m.c + b*m.f + c*m.i,
                d*m.a + e*m.d + f*m.g, d*m.b + e*m.e + f*m.h, d*m.c + e*m.f + f*m.i,
                g*m.a + h*m.d + i*m.g, g*m.b + h*m.e + i*m.h, g*m.c + h*m.f + i*m.i
        );
    }
    
}
