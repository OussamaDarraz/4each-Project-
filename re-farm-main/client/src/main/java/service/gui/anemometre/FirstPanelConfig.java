package service.gui.anemometre;

import service.functionnal.anemo.domain.ConfigurationAnemo;
import service.functionnal.anemo.service.AnemoService;
import service.gui.anemometre.ui.ButtonUI;
import service.gui.anemometre.ui.MaterialColor;
import service.gui.anemometre.ui.SelectOptions;
import service.gui.anemometre.ui.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FirstPanelConfig extends JPanel {
    public FirstPanelConfig() {
        AnemoService anemoService = new AnemoService();
        this.setLayout(new BorderLayout(20, 20));
        setBackground(MaterialColor.FULLWHITE);
        this.add(new JLabel("CONFIGURATION", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel jPanelcontent = new JPanel(new GridLayout(5, 1, 20, 20));
        this.add(jPanelcontent);
        jPanelcontent.setBackground(MaterialColor.FULLWHITE);
        JLabel jLabel0 = new JLabel("Anémomètre");
        JLabel jLabel = new JLabel("Vitesse De Vent");
        JLabel jLabel1 = new JLabel("Direction");
        JLabel jLabel2 = new JLabel("Statut D'Anemo");
        JTextField jTextField = new JTextField();
        JButton jButton = new ButtonUI("Configurer");
        JComboBox <String> jComboBox = new SelectOptions<>();
        jComboBox.addItem(null);
        for (String label : anemoService.getAllObjectLabel()) {
            jComboBox.addItem(label);
        }


        JComboBox <String> jComboBox3 = new SelectOptions<>();
        jComboBox3.addItem(null);
        jComboBox3.addItem("Nord");
        jComboBox3.addItem("NordEst");
        jComboBox3.addItem("Est");
        jComboBox3.addItem("SudEst");
        jComboBox3.addItem("Sud");
        jComboBox3.addItem("SudOuest");
        jComboBox3.addItem("Ouest");
        jComboBox3.addItem("NordOuest");
        JComboBox <String> jComboBox2 = new SelectOptions<>();
        jComboBox2.addItem(null);
        jComboBox2.addItem("Allumer");
        jComboBox2.addItem("Eteindre");
        jPanelcontent.add(addToPanel(jLabel0, jComboBox));
        jPanelcontent.add(addToPanel(jLabel, jTextField));
        jPanelcontent.add(addToPanel(jLabel1, jComboBox3));
        jPanelcontent.add(addToPanel(jLabel2, jComboBox2));

        // jPanelcontent.add(jLabel3);
        // jPanelcontent.add(jComboBox1);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(jButton);
        flow.setBackground(MaterialColor.FULLWHITE);
        jPanelcontent.add(flow);
        JPanel that = this;
       /* String[] columns = new String[] {
                "Produit", "Seuil Recommandé", };
        Object[][] data = new Object[][] {
                { "Tomate","20 km/h"  },
                { "Promme de terre","30 km/h"  },
                { "Carotte", "20 km/h"  },
                { "Haricot", "10 km/h" },
                { "Pommme", "80 km/h" },
                { "Orange", "80 km/h" },
                {"Banane", "80 km/h"},
                {"Courgette", "80 km/h"},

        };
        JTable table = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(table);
        table.setSize(1000,1000);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        scroll.setPreferredSize(new Dimension(50,500));
        //table.setRowHeight(50);
        JPanel jPanel = new JPanel(new BorderLayout(20,20));
        jPanel.add(scroll,BorderLayout.CENTER);
        jPanel.add(jPanelcontent,BorderLayout.NORTH);
        this.add(jPanel);*/
        PredictionGust predictionGust = new PredictionGust();
        this.add(predictionGust,BorderLayout.SOUTH);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Object selectedItem = jComboBox3.getSelectedItem();
                float f = 0;

                final Object anemoStatus = jComboBox2.getSelectedItem();
                final Object anemoLabel = jComboBox.getSelectedItem();
                final boolean speedFielded = ! Objects.equals( f,"");
                try {
                    f = Float.parseFloat(jTextField.getText());
                    if (
                            Objects.nonNull(anemoLabel)
                                    && (
                                    Objects.nonNull(anemoStatus) ||
                                            Objects.nonNull(selectedItem) ||
                                            speedFielded
                            )   && f>0
                    ) {
                        anemoService.configureAnemo(new ConfigurationAnemo(
                                0,
                                Objects.nonNull(selectedItem) ? String.valueOf(selectedItem) : null,
                                speedFielded ? f: -1f,
                                String.valueOf(anemoLabel),
                                Objects.nonNull(anemoStatus) ?Objects.equals(String.valueOf(anemoStatus), "Allumer") : null
                        ));
                        JOptionPane.showMessageDialog(that.getParent(), "Configuration Bien Enregistrée");
                    }
                    else if( f <= 0 ){

                        JOptionPane.showMessageDialog(that.getParent(), "Entrée Une Valeur Supérieure ou Égale 0");
                    }
                    else {
                        JOptionPane.showMessageDialog(that, "Error ! ");
                    }
                }
                catch (Exception ea){
                    JOptionPane.showMessageDialog(that.getParent(), " Erreur de Syntaxe: Entrer  Une valeur numerique \n pour  la configuration de vitesse ");
                    ea.printStackTrace();

                }
            }
        });

        setAlignmentY(Component.CENTER_ALIGNMENT);

    }


    private JPanel addToPanel(JComponent c1, JComponent c2) {
        JPanel p = new JPanel(new WrapLayout());
        p.add(c1);
        p.add(c2);
        c2.setSize(200, 40);
        c2.setPreferredSize(c2.getSize());
        p.setBackground(MaterialColor.FULLWHITE);
        return p;
    }




}
