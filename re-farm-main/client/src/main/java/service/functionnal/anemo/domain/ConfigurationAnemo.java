package service.functionnal.anemo.domain;

public class ConfigurationAnemo {
    private int id;
    private String direction;
    private Float speed;
    private String label;
    private Boolean status;

    public ConfigurationAnemo(int id, String direction, Float speed, String label, Boolean status) {
        this.id = id;
        this.direction = direction;
        this.speed = speed;
        this.label = label;
        this.status = status;
    }
    public ConfigurationAnemo(String label, Boolean status) {
        this.label = label;
        this.status = status;
    }

    public ConfigurationAnemo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConfigurationAnemo{" +
                "id=" + id +
                ", direction='" + direction + '\'' +
                ", speed=" + speed +
                ", label='" + label + '\'' +
                ", status=" + status +
                '}';
    }
}
