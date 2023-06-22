package service.gui.commercial.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.functionnal.commercial.ShowOfferAll;
import service.network.Client;

import java.io.IOException;
import java.util.ArrayList;

public class CliSelect {

        private Logger logger = LoggerFactory.getLogger(CliSelect.class);

        Client client = new Client();

 public ArrayList<String[]> selAll() throws  IOException {
        ArrayList<String[]> data = null;
        data = ShowOfferAll.run(client);

            return data;
    }




}
