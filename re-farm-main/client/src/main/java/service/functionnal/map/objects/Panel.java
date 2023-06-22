package service.functionnal.map.objects;


import java.util.ArrayList;

public class Panel extends MapObject {
    public Panel(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
        gp = new ArrayList<>();
        this.type = "PANEL";
    }
}
