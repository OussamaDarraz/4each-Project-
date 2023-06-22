package service.test;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ClientSocket;
import service.functionnal.anemo.domain.AnemometreValue;
import service.functionnal.anemo.domain.ConfigurationAnemo;
import service.functionnal.anemo.service.AnemoService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Anemo {
    static final Logger LOGGER = LoggerFactory.getLogger(Anemo.class);
    private static String[] args;
    private static final AnemoService anemoService = new AnemoService();
    public static void chargerConfig(){
        TypeReference< List<ConfigurationAnemo> > tRef = new TypeReference< List<ConfigurationAnemo> >() {};
        ObjectMapper mapper = new ObjectMapper();
        List<ConfigurationAnemo> list = new ArrayList<>();

        try{
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            list= (List<ConfigurationAnemo>) mapper.readValue(new File("client\\src\\main\\resources\\anemoConfi.json"),tRef);
            for(ConfigurationAnemo configurationAnemo: list){
                anemoService.configureAnemo(configurationAnemo);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void print(ArrayList<AnemometreValue> list){
        for(AnemometreValue t: list)
             {LOGGER.info(t.toString() +'\n');

        }

        }

    public static void printf(ArrayList<ConfigurationAnemo> list){
        for(ConfigurationAnemo t: list)
        {
            LOGGER.info(t.toString() +'\n');

        }}

     public static void main(String[] args) {


         //ConfigurationAnemo configurationAnemo =  new ConfigurationAnemo( "Anemo 3", true );
         List<AnemometreValue> anemometreValues= new ArrayList<>();
        // anemometreValues.add(new AnemometreValue(1,"hh","hhh",45,"gj","hh"));

        Options options = new Options();
        Option insertValues = Option.builder().longOpt("insertValues").build();
        options.addOption(insertValues);

        Option insertConfig = Option.builder().longOpt("insertConfig").build();
        options.addOption(insertConfig);

        Option selectConfig = Option.builder().longOpt("selectConfig").build();
        options.addOption(selectConfig);
        Option helpOption = Option.builder().option("h").desc("Show help output").build();
        options.addOption(helpOption);
        HelpFormatter hf = new HelpFormatter();
        CommandLineParser cliParser = new DefaultParser();
        try {
            CommandLine cli = cliParser.parse(options,args);
            if(cli.hasOption(helpOption)) {
                hf.printHelp("Anemo  Test Cli", options, true);
            } else if (cli.hasOption(insertValues)) {
               // anemometreValues.add(new AnemometreValue(1,"hh","hhh",45,"gj","hh"));
                anemoService.insertValues();
                LOGGER.info(" success insert values");

            } else if(cli.hasOption(insertConfig)){
                chargerConfig();
                LOGGER.info(" success insert config");

    } else if(cli.hasOption(selectConfig)){
               printf((ArrayList<ConfigurationAnemo>) anemoService.getListConfigurationAnemo());
            }}catch (ParseException e) {
            hf.printHelp("Anemo  Test CLi", options, true);
        }
        catch (Exception e){
            e.printStackTrace();
    }
}}
