package service.gui.pann.utils;

import service.functionnal.pann.domain.PositionChangedValues;
import service.functionnal.pann.service.SolarService;

import java.util.ArrayList;
import java.util.List;

public class Applying {
    static SolarService solarService = new SolarService();

    public static void algoapply ( double energy )
    {
        List<PositionChangedValues> total = solarService.calcTotal(energy);
        for ( PositionChangedValues a : total) {
            solarService.createaPanel();
            solarService.findaPanel() ;
            solarService.paneltoMap(solarService.findaPanel(), a.getX() , a.getY());

        }


    }
}
