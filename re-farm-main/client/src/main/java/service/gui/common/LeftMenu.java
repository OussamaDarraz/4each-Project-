package service.gui.common;

import service.gui.anemometre.PageAnemo;
import service.gui.anemometre.ui.MaterialColor;
import service.gui.commercial.MainOffer ;
import service.gui.map.MainMap;
import service.gui.pann.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftMenu extends JPanel implements ActionListener {
    private final JFrame parentFame;
    public LeftMenu(JFrame parentFame){
        this.parentFame = parentFame;
        this.setLayout(new GridLayout(5, 1));
        JButton menu = new JButton("MENU");
        menu.setBackground(MaterialColor.GREY_500);
        menu.setBorderPainted(false);
        menu.setOpaque(true);
        menu.setForeground(Color.WHITE);
        menu.setActionCommand("MENU_BTN");
        menu.addActionListener( this);
        JButton  plan = new JButton("PLAN");
        plan.setBackground(MaterialColor.GREY_500);
        plan.setBorderPainted(false);
        plan.setOpaque(true);
        plan.setForeground(Color.WHITE);
        plan.setActionCommand("MAP_BTN");
        plan.addActionListener(this);
        JButton anemo = new JButton("ANNEMO");
        anemo.setBackground(MaterialColor.GREY_500);
        anemo.setBorderPainted(false);
        anemo.setOpaque(true);
        anemo.setForeground(Color.WHITE);
        anemo.setActionCommand("ANEMO_BTN");
        anemo.addActionListener( this);
        JButton solaire = new JButton("SOLAIRE");
        solaire.setBackground(MaterialColor.GREY_500);
        solaire.setBorderPainted(false);
        solaire.setOpaque(true);
        solaire.setForeground(Color.WHITE);
        solaire.setActionCommand("SOLAIRE_BTN");
        solaire.addActionListener( this);
        JButton offre = new JButton("COMMERCIAL OFFRE");
        offre.addActionListener(e -> {
            MainOffer  mainOffer = new MainOffer ();
            mainOffer.setVisible(true);
            parentFame.setVisible(false);
        });
        offre.setBackground(MaterialColor.GREY_500);
        offre.setBorderPainted(false);
        offre.setOpaque(true);
        offre.setForeground(Color.WHITE);
        this.add(menu);
        this.add(plan);
        this.add(anemo);
        this.add(solaire);
        this.add(offre);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            if (btn.getActionCommand().equals("ANEMO_BTN")) {
                if( ! (parentFame instanceof PageAnemo)) {
                    parentFame.setVisible(false);
                    new PageAnemo();
                } else {
                    parentFame.setVisible(true);
                }
            } else if (btn.getActionCommand().equals("MENU_BTN"))
            {
                if( ! (parentFame instanceof Menu)) {
                    parentFame.setVisible(false);
                    new Menu();
                } else {
                    parentFame.setVisible(true);
                }
            }
            else if (btn.getActionCommand().equals("SOLAIRE_BTN"))
            {
                if( !(parentFame instanceof MainPanel)) {
                    parentFame.setVisible(false);
                    new MainPanel();
                } else {
                    parentFame.setVisible(true);
                }
            }
            else if (btn.getActionCommand().equals("MAP_BTN"))
            {
                if( !(parentFame instanceof MainPanel)) {
                    parentFame.setVisible(false);
                    try {
                        new MainMap();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    parentFame.setVisible(true);
                }
            }

        }

    }
}
