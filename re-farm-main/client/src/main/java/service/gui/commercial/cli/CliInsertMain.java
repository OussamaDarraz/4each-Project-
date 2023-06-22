package service.gui.commercial.cli;

import java.io.IOException;

import static service.gui.commercial.cli.CliInsert.client;

public class CliInsertMain {
    public static void main(String[] args) throws IOException {

            CliInsert cliinsert= new CliInsert();


            cliinsert.selectChecker(client, "timora",10);


    }
}
