package service.gui.common;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public Menu(){
        ImageIcon img = new ImageIcon("C:\\Users\\hamza\\projectPds\\re-farm\\client\\src\\main\\resources\\logo_App.png");
        this.setIconImage(img.getImage());
        this.setVisible(true);
        this.setSize(1000,1000);
        JPanel jPanelMain = new JPanel(new BorderLayout(20,20));
        LeftMenu leftMenu = new LeftMenu(this);
        ContentPane contentPane = new ContentPane(this);
        jPanelMain.add(leftMenu,BorderLayout.WEST);
        jPanelMain.add(contentPane,BorderLayout.CENTER);
        this.add(jPanelMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }
}
