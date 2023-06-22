package service.gui.tools;

import javax.swing.*;

public class Confirm {
    public static boolean run(String question) {
         return JOptionPane.showConfirmDialog(null, question) == 0;
    }
}
