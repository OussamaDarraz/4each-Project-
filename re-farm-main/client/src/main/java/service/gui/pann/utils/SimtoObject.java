package service.gui.pann.utils;

import java.io.Serializable;

public class SimtoObject implements Serializable {
    private int x;
    private int y ;
    private double niveau_ensoleillement ;
    private double energy_generated ;


    public SimtoObject(int x, int y, double niveau_ensoleillement, double energy_generated) {
        this.x = x;
        this.y = y;
        this.niveau_ensoleillement = niveau_ensoleillement ;
        this.energy_generated = energy_generated;
    }

    public SimtoObject() {}

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


    public double getEnergy_generated() {
        return energy_generated;
    }

    public void setEnergy_generated(double energy_generated) {
        this.energy_generated = energy_generated;
    }

    public double getNiveau_ensoleillement() {
        return niveau_ensoleillement;
    }

    public void setNiveau_ensoleillement(double niveau_ensoleillement) {
        this.niveau_ensoleillement = niveau_ensoleillement;
    }
}
