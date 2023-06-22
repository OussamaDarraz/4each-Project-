package service.functionnal.anemo.service;


import service.functionnal.anemo.domain.AnemometreValue;
import service.functionnal.anemo.domain.ConfigurationAnemo;
import service.functionnal.anemo.repository.AnemoRepository;

import java.util.List;

public class AnemoService {
    private final AnemoRepository anemoRepository;

    public AnemoService() {
        this.anemoRepository = new AnemoRepository();
    }


    public List<AnemometreValue> getAllAnemoValueByLabel(String label) {
        return anemoRepository.findByObjectLabel(label);
    }

    public List<String> getAllObjectLabel() {

        return anemoRepository.findAllObjectLabel();
    }
    public void configureAnemo(ConfigurationAnemo configurationAnemo) {
        anemoRepository.saveOrUpdateAnemoConfig(configurationAnemo);
    }

    public int differenceConfig(String label) {
        return anemoRepository.difference(label);
    }
    public int differenceGust(String label) {
        return anemoRepository.differenceGust(label);
    }
    public int getNumberAnemoConfigured(){return anemoRepository.numberConfigured();}
    public int getNumberNoConfigured(){return anemoRepository.numberNoConfigured();}
    public List<ConfigurationAnemo>getListConfigurationAnemo(){return  anemoRepository.fetchAllAnemoConfigured();}
    public void insertValues(){ anemoRepository.insertValues();}
    }
