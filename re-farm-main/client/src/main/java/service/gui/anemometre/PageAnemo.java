package service.gui.anemometre;

import service.gui.anemometre.ui.MaterialColor;
import service.gui.common.LeftMenu;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

public class PageAnemo extends JFrame {
    public PageAnemo(){

        // Removes the dotted border around controls which is not consistent with Windows
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("ToggleButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("Roboto-Bold.ttf")) {
            setUIFont (new javax.swing.plaf.FontUIResource(Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(14f)));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        ImageIcon img = new ImageIcon("C:\\Users\\hamza\\projectPds\\re-farm\\client\\src\\main\\resources\\logo_App.png");
        this.setIconImage(img.getImage());

        // ways to remove it from other controls...
        UIManager.put("CheckBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("Slider.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        // figure out combobox
        UIManager.put("ComboBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        this.setVisible(true);
        this.setSize(1200,800);
        FirstPanelConfig firstPanelConfig = new FirstPanelConfig();
        SecondPanelConsult secondPanelConsult = new SecondPanelConsult();
        //LeftMenu leftMenu = new LeftMenu(this);
        JPanel jPanelMain = new JPanel(new BorderLayout(20,20));
        JPanel header = new JPanel();
        header.add(new JLabel("ANNEMO"));
        JSplitPane panelCenter = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
        jPanelMain.add(header, BorderLayout.NORTH);
        JSplitPane contentPanel = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
        contentPanel.setLeftComponent(firstPanelConfig);
        contentPanel.setRightComponent(secondPanelConsult);
        contentPanel.setResizeWeight(0.55);
        contentPanel.setEnabled(false);
        contentPanel.setBackground(MaterialColor.FULLWHITE);
        contentPanel.setOpaque(false);

        LeftMenu leftMenu= new LeftMenu(this);
        panelCenter.setLeftComponent(leftMenu);
        panelCenter.setRightComponent(contentPanel);

        panelCenter.setEnabled(false);

        contentPanel.setBorder(null);
        panelCenter.setBorder(null);

        jPanelMain.add(panelCenter,BorderLayout.CENTER);
        this.add(jPanelMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.invalidate();
        this.repaint();
        this.revalidate();
    }

    private static void setUIFont (javax.swing.plaf.FontUIResource f){
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }
}
