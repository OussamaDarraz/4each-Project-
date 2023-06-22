package service.gui.anemometre;

import service.functionnal.anemo.service.AnemoService;
import service.gui.anemometre.ui.MaterialColor;
import service.gui.anemometre.ui.SelectOptions;
import service.gui.anemometre.ui.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PredictionGust extends JPanel {
    AnemoService anemoService = new AnemoService();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    public PredictionGust() {
        this.setLayout(new BorderLayout(20,20));
        this.add(new JLabel(" PREVISION DES RAFALES", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(3, 2, 20, 20));
        JLabel jLabel = new JLabel("Anémomètre");
        JLabel jLabel1 = new JLabel(" Etat D'Anémometre par rapport la valeur configurée:");
        JLabel jlabel0 = new JLabel("Etat  D'Anémometre: D'avoir une rafale:");
        //Raffale proche, Raffale loin
        JComboBox <String> jComboBox = new SelectOptions<>();
        jComboBox.addItem(null);
        for (String label : anemoService.getAllObjectLabel()) {
            jComboBox.addItem(label);
        }
        panel.add(addToPanel(jLabel, jComboBox));
        panel.add(addToPanel(jLabel1, jLabel2));
        panel.add(addToPanel(jlabel0,jLabel3));
        this.add(panel);
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String newLabel = String.valueOf(jComboBox.getSelectedItem());
                 float res = anemoService.differenceConfig(newLabel);
               float resGust = anemoService.differenceGust(newLabel);
               gustMethode(res,resGust);

            }
        });
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
    private void gustMethode(float diff,float gust){
        if (diff==0){
            jLabel2.setText("SituationCritique!!!");
            jLabel2.setForeground(MaterialColor.RED_700);

        } else if (diff>=-3){
            jLabel2.setText("SituationVaÊtreCritique!!!");
            jLabel2.setForeground(MaterialColor.ORANGE_700);
        }else{
            jLabel2.setText("Normal");
            jLabel2.setForeground(MaterialColor.BLUE_700);
        }
       if(gust >=18) {

                jLabel3.setText("Attention:RafaleProche !!");
                jLabel3.setForeground(MaterialColor.RED_700);

       }else{
                jLabel3.setText("VentCalme");
                jLabel3.setForeground(MaterialColor.BLUE_700);



        }}
}

