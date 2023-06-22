package service.gui.pann.utils;

import service.functionnal.pann.domain.PositionChangedValues;
import service.functionnal.pann.service.SolarService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnergyAlgo {
    static SolarService solarService = new SolarService();

    public static Double[] EnergyCalculingA(double energy) {
        List<Integer> toId;
        double energyuna;
        Double[] energypan = new Double[2];
        double energysc;
        double panelnum;
        double energyce = 0.0;


        toId = solarService.getAllIdByType("type_a");
        energyuna = (solarService.totalEnergyByType("type_a")).get(0);
        energysc = toId.size() * energyuna;
        if (energy == energyuna || energy < energyuna) {
            energy = energyuna;
            panelnum = 1;

        } else if (energy >= energysc) {
            energy = energysc;
            panelnum = toId.size();
        } else {
            energyce = energyce + energyuna;
            int i = 1;
            while (energy > energyce) {
                i++;
                energyce = energyce + energyuna;
            }
            energy = energyce;
            panelnum = i;

        }
        energypan[0] = energy;
        energypan[1] = panelnum;
        return energypan;

    }

    public static Double[] EnergyCalculingB(double energy) {
        List<Integer> toId;
        double energyunb;
        Double[] energypan = new Double[2];
        double energysc;
        double panelnum;
        double energyce = 0.0;


        toId = solarService.getAllIdByType("type_b");
        energyunb = (solarService.totalEnergyByType("type_b")).get(0);
        energysc = toId.size() * energyunb;
        if (energy == energyunb || energy < energyunb) {
            energy = energyunb;
            panelnum = 1;

        } else if (energy >= energysc) {
            energy = energysc;
            panelnum = toId.size();
        } else {
            energyce = energyce + energyunb;
            int i = 1;
            while (energy > energyce) {
                i++;
                energyce = energyce + energyunb;
            }
            energy = energyce;
            panelnum = i;

        }
        energypan[0] = energy;
        energypan[1] = panelnum;
        return energypan;

    }

    public static Double[] EnergyCalculingC(double energy) {
        List<Integer> toId;
        double energyunc;
        Double[] energypan = new Double[2];
        double energysc;
        double panelnum;
        double energyce = 0.0;


        toId = solarService.getAllIdByType("type_c");
        energyunc = (solarService.totalEnergyByType("type_c")).get(0);
        energysc = toId.size() * energyunc;
        if (energy == energyunc || energy < energyunc) {
            energy = energyunc;
            panelnum = 1;

        } else if (energy >= energysc) {
            energy = energysc;
            panelnum = toId.size();
        } else {
            energyce = energyce + energyunc;
            int i = 1;
            while (energy > energyce) {
                i++;
                energyce = energyce + energyunc;
            }
            energy = energyce;
            panelnum = i;

        }
        energypan[0] = energy;
        energypan[1] = panelnum;
        return energypan;

    }

    public static Double[] EnergyCalculingD(double energy) {
        List<Integer> toId;
        double energyund;
        Double[] energypan = new Double[2];
        double energysc;
        double panelnum;
        double energyce = 0.0;


        toId = solarService.getAllIdByType("type_d");
        energyund = (solarService.totalEnergyByType("type_d")).get(0);
        energysc = toId.size() * energyund;
        if (energy == energyund || energy < energyund) {
            energy = energyund;
            panelnum = 1;

        } else if (energy >= energysc) {
            energy = energysc;
            panelnum = toId.size();
        } else {
            energyce = energyce + energyund;
            int i = 1;
            while (energy > energyce) {
                i++;
                energyce = energyce + energyund;
            }
            energy = energyce;
            panelnum = i;

        }
        energypan[0] = energy;
        energypan[1] = panelnum;
        return energypan;

    }


    public static Double[] EnergyCalculing(double energy) {
        List<Double> energyp;
        double energyTotal = 0;
        double energymin = 0;
        double energyce = 0;
        double panelnum = 0;

        double i = 1 ;
        List<PositionChangedValues> total = solarService.calcTotal(energy);
        Double[] energypan = new Double[2];
        energyp = solarService.findAllEnergy();
        energypan[0] = 0.0 ;
        for ( PositionChangedValues ad : total)
        {
            energypan[0] = energypan[0]+ad.getEnergy_generated();

        }

        energypan[1] = total.size() + 0.0;

        return energypan;
    }
}







