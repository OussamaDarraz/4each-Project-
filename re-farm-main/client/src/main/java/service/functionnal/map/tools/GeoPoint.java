package service.functionnal.map.tools;

public class GeoPoint {
    private int x;
    private int y;
    private int order;

    public GeoPoint(int x, int y, int order) {
        this.x = x;
        this.y = y;
        this.order = order;
    }

    /*** GETTER / SETTER ***/
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getOrder() { return order; }
    public void setOrder(int order) { this.order = order; }
}
