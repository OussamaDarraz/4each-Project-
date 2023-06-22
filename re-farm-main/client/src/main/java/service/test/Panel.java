package service.test;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.functionnal.pann.service.SolarService;

import static service.gui.pann.utils.EnergyAlgo.EnergyCalculing;

public class Panel {
    static final Logger LOGGER = LoggerFactory.getLogger(Panel.class);
    private static final SolarService solarService = new SolarService();
    public static void main(String[] args) {
        double t = 0.0 ;
        Options options = new Options();
        Option panel = Option.builder().longOpt("panelnum").desc("Display how many panels are inserted manually and automatically into the map. ").build();
        options.addOption(panel);
        Option simulation = Option.builder().longOpt("simulation").desc("Start an energy simulation.").hasArg().build();
        options.addOption(simulation);
        Option helpOption = Option.builder().option("h").desc("Display help.").build();
        options.addOption(helpOption);
        HelpFormatter hf = new HelpFormatter();
        CommandLineParser cliParser = new DefaultParser();
        try {
            CommandLine cli = cliParser.parse(options,args);
            if(cli.hasOption(helpOption)) {
                hf.printHelp("Test-Re-Farme-Project-Panel-Cli", options, true);
            } else if (cli.hasOption(panel)) {
                LOGGER.info("Panel_num_test");
                LOGGER.info("the total of solar panels is : " +solarService.totalPanel());


            } else if(cli.hasOption(simulation)){
                LOGGER.info("simulation_test");
                double  energy = Double.parseDouble(cli.getOptionValue(simulation));
                double a = EnergyCalculing(energy)[0];
                int b = EnergyCalculing(energy)[1].intValue();
                if ( b == 0 ) {
                    LOGGER.info("you cannot generate this amount of energy");
                }
                else {
                    LOGGER.info("Solar Panel number : " +b);
                    LOGGER.info("Energy generated : " +a );
                }



            } }catch (ParseException e) {
            hf.printHelp("Test-Re-Farme-Project-Anemo-Panel-Cli", options, true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}