package service.gui.map.objects;

import service.functionnal.map.tools.GeoPoint;
import service.functionnal.map.objects.MapObject;

import java.awt.*;

public class BuildingObj extends MainObj {
    public BuildingObj(Graphics g, MapObject mo, int width, int height) {
        this.width = width;
        this.height = height;
        this.g = g;
        this.mo = mo;
        g.setColor(new Color(50, 50, 50));
        for (GeoPoint gp : mo.getGeoPoints()) {
            addPoint(setW(gp.getX()), setH(gp.getY()));
        }

    }
}
