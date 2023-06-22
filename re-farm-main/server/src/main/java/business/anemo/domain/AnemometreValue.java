package business.anemo.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnemometreValue implements Serializable {
    private int id;
    private String position;
    private String direction;
    private float speed;
    private String timedate;
    private String label;


    public AnemometreValue(int id, String position, String direction, float speed, String timedate, String label) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.timedate = timedate;
        this.label = label;
    }

    public AnemometreValue(int id, String position, String direction, float speed, String timedate) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.timedate = timedate;
    }

    public AnemometreValue(String position, String direction, float speed, String timedate) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.timedate = timedate;
    }

    public AnemometreValue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}