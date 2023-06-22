package service.functionnal.pann.service;

import service.functionnal.pann.domain.PositionChangedValues;
import service.functionnal.pann.domain.PositionValues;
import service.functionnal.pann.domain.SolarPanelValue;
import service.functionnal.pann.repository.SolarRepository;


import java.util.List;

public class SolarService {
    private final SolarRepository solarRepository ;

    public SolarService() {
        this.solarRepository = new SolarRepository();
    }

    public List<SolarPanelValue> getAllSolarPanelById (int id ) {return solarRepository.findObjectById(id);}
    public List<String> getAllSolarPanelByLabel () { return solarRepository.findAllObjectByLabel(); }
    public List<Double> getEnergyValue() {return solarRepository.getEnergyValue();}
    public List<Double> totalEnergyByType( String type ) {return solarRepository.totalEnergyByType(type); }
    public List<Integer> getAllIdByType ( String type) { return solarRepository.findIdByType(type);}
    public List <PositionValues> getPlaces () { return solarRepository.findPlaces();}
    public List <PositionChangedValues> getAllPlacesWenergie () { return solarRepository.findPlacesWEnergy();}
    public List<Double> findAllEnergy () { return solarRepository.findAllEnergy();}
    public void insertanobject (String obj_label) { solarRepository.insertanobject(obj_label);}
    public void updateapanel ( String panel_type) {solarRepository.updateapanel(panel_type);}
    public void configtheplace ( int sunshine_lvl) {solarRepository.configtheplace(sunshine_lvl);}
    public List <PositionChangedValues> calcTotal (double energy) { return solarRepository.findCalc(energy);}
    public void createaPanel() {solarRepository.createtoMap();}
    public int findaPanel() {return solarRepository.findAPanel();}
    public void paneltoMap(int id , int x ,int y) { solarRepository.insertaPanel(id,x,y);}
    public int totalPanel() {return solarRepository.totalPanel();}
}
