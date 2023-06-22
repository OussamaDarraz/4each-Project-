package service.gui.commercial.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.functionnal.commercial.CreateOfferInsert;
import service.functionnal.commercial.ProdOfferAll;
import service.network.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CliInsert {
    private Logger logger = LoggerFactory.getLogger(CliInsert.class);

    static Client client = new Client();

    public void selectChecker(Client client, String product, int weight) throws IOException {

        CreateOfferInsert.define1(client, product, weight);


    // lire les resultats de l'insertion opérée
    ArrayList<String[]> data = null;

    data = ProdOfferAll.run(client);
    for (String[] datim : data) {
        logger.info(Arrays.toString(datim));
    }

    }


}

