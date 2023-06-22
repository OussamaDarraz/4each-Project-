package service.gui.map.objects;

import service.functionnal.map.tools.GeoPoint;
import service.functionnal.map.objects.MapObject;

import java.awt.*;

public class FieldObj extends MainObj {
    public FieldObj(Graphics g, MapObject mo, int width, int height) {
        this.width = width;
        this.height = height;
        this.g = g;
        this.mo = mo;
        g.setColor(new Color(9, 82, 40));

        if(mo.getGeoPoints().size() > 2) {
            for (GeoPoint gp : mo.getGeoPoints()) {
                addPoint(setW(gp.getX()), setH(gp.getY()));
            }
        }
        else if(mo.getGeoPoints().size() > 1) {
            for (GeoPoint gp : mo.getGeoPoints()) {
                g.setColor(new Color(255, 0, 255));
                addPoint(setW(gp.getX()), setH(gp.getY()));
                addPoint(setW(gp.getX())+5, setH(gp.getY())+5);
            }
        }
        else {
            for (GeoPoint gp : mo.getGeoPoints()) {
                int val = 6;
                g.setColor(new Color(255, 0, 255));
                int x = setW(gp.getX()), y = setH(gp.getY());
                addPoint(x, y+val);
                addPoint(x+val, y);
                addPoint(x, y-val);
                addPoint(x-val, y);
            }
        }
    }
}
