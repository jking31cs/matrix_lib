package com.jking31cs.shape;

import com.jking31cs.triangle.Color;
import com.jking31cs.triangle.Triangle;
import com.jking31cs.vector.Vector3D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jking31cs on 6/9/15.
 */
public class Shape {
    public final List<Triangle> triangleList;

    public Shape(List<Triangle> triangleList) {
        this.triangleList = triangleList;
    }

    public static Shape readFromFile(File file) throws IOException {
        List<Triangle> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] s = line.split("\\s+");
            if (s.length != 9) {
                throw new RuntimeException("Invalid format");
            }
            list.add(new Triangle(
                new Vector3D(Float.parseFloat(s[0]),Float.parseFloat(s[1]),Float.parseFloat(s[2])),
                new Vector3D(Float.parseFloat(s[3]),Float.parseFloat(s[4]),Float.parseFloat(s[5])),
                new Vector3D(Float.parseFloat(s[6]),Float.parseFloat(s[7]),Float.parseFloat(s[8])),
                new Color(1,0,0),
                new Color(1,1,1)
            ));
        }
        return new Shape(list);
    }
}
