package service.gui.pann.utils;
import service.functionnal.pann.service.SolarService;

import java.util.List;

public class EnergyChecking {
    static SolarService solarService = new SolarService();
    public EnergyChecking (){}
    public static double EnergywType ()
    {
        List<Double> energyp;
        double energyTotal = 0;
        energyp = solarService.findAllEnergy();

        for (Double a : energyp) {
            energyTotal = energyTotal + a;
        }



        return energyTotal ;
    }
}
