package service.gui.map.objects;

import service.functionnal.map.objects.MapObject;

import java.awt.*;

public class MainObj extends Polygon {
    protected Graphics g;
    protected MapObject mo;
    protected int width;
    protected int height;

    public void addToMap() {
        g.fillPolygon(this);
    }

    protected int setW(int w) {
        float per = (float) w/100;
        return (int) (width * per);
    }

    protected int setH(int h) {
        float per = (float) h/100;
        return (int) (height * per);
    }
}
