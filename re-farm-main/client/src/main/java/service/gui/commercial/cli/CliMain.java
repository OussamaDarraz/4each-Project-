
package service.gui.commercial.cli;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

import static service.gui.commercial.cli.CliInsert.client;


public class CliMain {

    static Scanner sc = new Scanner(System.in);
    static Logger log = LoggerFactory.getLogger(CliMain.class);

    private static int c = 0;
    private static int k = 0;

    public static void main(String[] arg) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);
        boolean k = true;

        while (c != 5) {
            log.info("Select a command to execute : ");
            log.info("1 : test insertion product");
            log.info("2 : test selection offer");

            log.info("3 : exit");


            while (k) {
                log.info("Select a value : ");
                c = sc.nextInt();
                switch (c) {
                    case (1):
                        System.out.println("ok");
                        new CliInsert().selectChecker(client,"Lait",5);
                        break;
                    case (2):
                        new CliSelect().selAll();
                        break;

                    case (3):
                        System.out.println("End of Test, Thanks!");
                        System.exit(0);

                        break;

                        case (4):
                        k = false;
                        break;

                }

            }
        }
    }
}

