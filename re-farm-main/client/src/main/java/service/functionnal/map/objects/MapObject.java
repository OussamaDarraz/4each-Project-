package service.functionnal.map.objects;

import service.functionnal.map.tools.GeoPoint;
import service.gui.map.objects.MainObj;

import java.util.List;

public abstract class MapObject {
    protected int id;
    protected String label;
    protected String description;
    protected String type;
    protected List<GeoPoint> gp;
    protected MainObj obj;

    public void addPoint(GeoPoint geop) {
        gp.add(geop);
    }

    /*** GETTER / SETTER ***/
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label;  }
    public String getDescription() { return description;  }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public List<GeoPoint> getGeoPoints() { return gp; }

    public void setObj(MainObj obj) {
        this.obj = obj;
    }

    public MainObj getObj() {
        return obj;
    }
}
