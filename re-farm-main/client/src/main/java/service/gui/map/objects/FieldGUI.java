package service.gui.map.objects;

import service.functionnal.map.tools.GeoPoint;

import java.awt.*;
import java.util.Arrays;

public class FieldGUI extends Polygon {
    GeoPoint[] points;

    public FieldGUI(GeoPoint[] points) {
       this.points = points;
       sortGeoPoints();
       createField();
    }

    private void createField() {
        Arrays.stream(points).sorted();
        for (GeoPoint p : points) {
            addPoint(p.getX(), p.getY());
        }
    }

    private void sortGeoPoints() {
       boolean move = true;
       int j = 0;
       while (move) {
           move = false;
           j = j + 1;
           for (int i = 0; i < points.length - j; i++) {
               if (points[i].getOrder() > points[i+1].getOrder()) {
                   move = true;
                   GeoPoint tmp = points[i];
                   points[i] = points[i+1];
                   points[i+1] = tmp;
               }
           }
       }
    }
}
