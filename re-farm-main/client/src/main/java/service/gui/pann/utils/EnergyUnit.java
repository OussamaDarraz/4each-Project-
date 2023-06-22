package service.gui.pann.utils;

import service.functionnal.pann.service.SolarService;

public class EnergyUnit {
    static SolarService solarService = new SolarService();
    public static double EnergyOfUnit ( String type) {
        double totalEnergie = 0.0;
        if (type == "Type_a") {
            totalEnergie = solarService.totalEnergyByType("type_a").get(0);
        } else if (type == "Type_b") {
            totalEnergie = solarService.totalEnergyByType("type_b").get(0);
        } else if (type == "Type_c") {
            totalEnergie = solarService.totalEnergyByType("type_c").get(0);

        } else if (type == "Type_d") {
            totalEnergie = solarService.totalEnergyByType("type_d").get(0);
        }
        return  totalEnergie;
    }
}

