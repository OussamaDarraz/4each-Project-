package business.pann.domain;

import java.io.Serializable;

public class PositionValues implements Serializable {
    private int x;
    private int y ;
    private int niveau_ensoleillement ;

    public PositionValues(int x, int y, int niveau_ensoleillement) {
        this.x = x;
        this.y = y;
        this.niveau_ensoleillement = niveau_ensoleillement;
    }
    public PositionValues() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNiveau_ensoleillement() {
        return niveau_ensoleillement;
    }

    public void setNiveau_ensoleillement(int niveau_ensoleillement) {
        this.niveau_ensoleillement = niveau_ensoleillement;
    }
}
