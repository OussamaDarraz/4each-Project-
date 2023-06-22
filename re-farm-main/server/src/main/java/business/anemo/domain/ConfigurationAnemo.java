package business.anemo.domain;

public class ConfigurationAnemo {
    private int id;
    private String direction;
    private float speed;
    private String label;
    private boolean status;

    public ConfigurationAnemo(int id, String direction, float speed, String label, boolean status) {
        this.id = id;
        this.direction = direction;
        this.speed = speed;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ConfigurationAnemo{" + "id=" + id +
                ", direction='" + direction + '\'' +
                ", speed=" + speed +
                ", label='" + label + '\'' +
                ", status=" + status +
                '}';
    }
}
