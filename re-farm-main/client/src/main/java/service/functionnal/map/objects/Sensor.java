package service.functionnal.map.objects;

import service.functionnal.map.tools.GeoPoint;

import java.util.ArrayList;

public class Sensor extends MapObject {

    public Sensor(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
        gp = new ArrayList<>();
        this.type = "SENSOR";
    }

    public void setPosition(GeoPoint gp) {
        this.gp.add(0, gp);
    }
    public GeoPoint getPosition() { return this.gp.get(0); }
}
