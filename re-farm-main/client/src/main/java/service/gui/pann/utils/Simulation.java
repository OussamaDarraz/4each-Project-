package service.gui.pann.utils;

import service.functionnal.pann.domain.PositionChangedValues;
import service.functionnal.pann.domain.PositionValues;
import service.functionnal.pann.service.SolarService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Simulation {
    static SolarService solarService = new SolarService();
    public  static List<PositionChangedValues> ad = solarService.getAllPlacesWenergie();
    public  static  ArrayList<SimtoObject> EnergySimul (double energy)
    {   ArrayList<SimtoObject> sim = new ArrayList<>();
        List<PositionChangedValues> total = solarService.calcTotal(energy);
        for ( PositionChangedValues a : total) {
            SimtoObject re = new SimtoObject();
            re.setX(a.getX());
            re.setY(a.getY());
            re.setNiveau_ensoleillement(a.getNiveau_ensoleillement());
            re.setEnergy_generated(a.getEnergy_generated());
            sim.add(re);
        }

        return sim ;

    }




}
