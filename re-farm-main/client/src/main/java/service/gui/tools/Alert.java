package service.gui.tools;

import javax.swing.*;

public class Alert extends JOptionPane {
    public Alert(String msg) {
        JOptionPane optionPane = new JOptionPane(msg,JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Alerte");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
}
