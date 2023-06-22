package business.pann.service;

import business.pann.domain.PositionChangedValues;
import business.pann.domain.PositionValues;
import business.pann.domain.SolarPanelValue;
import business.pann.repository.SolarRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SolarService {
    private final SolarRepository solarRepository ;

    public SolarService(Connection connection) {
        this.solarRepository = new SolarRepository(connection);
    }

    public List<SolarPanelValue> getAllSolarPanelById ( int id )
    {
        return solarRepository.FindObjectById(id);
    }
    public List<String> getAllSolarPanelByLabel () { return solarRepository.findAllObjectByLabel(); }
    public List<Double> getEnergieValue () {return solarRepository.GetEnergyValue();}
    public List<SolarPanelValue> getAllSolarPanelByType (String type) {return solarRepository.FindObjectByType(type);}
    public List<Double> totalEnergyByType (String type) { return solarRepository.TotalEnergyByType(type);}
    public List<Integer> getAllIdByType ( String type) { return solarRepository.FindIdByType(type);}
    public List<PositionValues> getAllPlaces () { return solarRepository.FindAllPlaces();}
    public void insertintoObject (String obj_label) { solarRepository.InsertIntoObject(obj_label);}
    public void updateapanel ( String panel_type) { solarRepository.UpdateaPanel(panel_type);}
    public void configtheplace (  int sunshine_lvl ) { solarRepository.ConfigthePlace(sunshine_lvl);}
    public List<PositionChangedValues> getAllPlacesWenergie() { return solarRepository.FindAllPlacesWenergie();}
    public List<Double> FindAllEnergy()  { return solarRepository.FindAllEnergy();}
    public List<PositionChangedValues> findTotalByEnergie(double energy) { return solarRepository.findTotalByEnergie(energy);}
    public void createaPanel() {solarRepository.Createapanel();}
    public int findaPanel() {return solarRepository.Findeapanel();}
    public void paneltoMap(int id , int x , int y ) { solarRepository.PaneltotheMap(id,x,y);}
    public int totalPanel() {return solarRepository.Totalpan();}

}




