package service.gui.map.objects;

import service.functionnal.map.tools.GeoPoint;
import service.functionnal.map.objects.MapObject;

import java.awt.*;

public class SensorObj extends MainObj {
    public SensorObj(Graphics g, MapObject mo, int width, int height) {
        this.width = width;
        this.height = height;
        this.g = g;
        this.mo = mo;

        int val = 10;
        g.setColor(new Color(255, 0, 0));
        GeoPoint gp = mo.getGeoPoints().get(0);
        int x = setW(gp.getX()), y = setH(gp.getY());
        addPoint(x, y+val);
        addPoint(x+val, y);
        addPoint(x, y-val);
        addPoint(x-val, y);

    }
}
