package business.anemo.service;

import business.anemo.domain.AnemometreValue;
import business.anemo.domain.ConfigurationAnemo;
import business.anemo.repository.AnemoRepository;

import java.sql.Array;
import java.sql.Connection;
import java.util.List;

public class AnemoService {
    private final AnemoRepository anemoRepository;

    public AnemoService(Connection connection) {
        this.anemoRepository = new AnemoRepository(connection);
    }

    public List<AnemometreValue> getAllAnemoValueByLabel(String label) {
        return anemoRepository.findByObjectLabel(label);
    }

    public List<String> getAllObjectLabel() {
        return anemoRepository.findAllObjectLabel();
    }

    public void configureAnemo(ConfigurationAnemo anemoConfig) {
        anemoRepository.saveOrUpdateAnemoConfig(anemoConfig);
    }
    public int differenceBetweenSpeed(String label){
        return anemoRepository.differenceBetweenSpeed(label);
    }
    public int differenceBetweenSpeedGust(String label){return  anemoRepository.differenceBetweenSpeedGust(label);}
    public int getNumberAnemoConfigured(){return  anemoRepository.numberAnemoConfigured();}
    public int getNumberAnemo(){return  anemoRepository.numberAnemo();}
    public  List<ConfigurationAnemo>getAllConfiguration(){return anemoRepository.testSelectAllConfiguration();}
    public void insertValues(){anemoRepository.Insertvalues();}
}
