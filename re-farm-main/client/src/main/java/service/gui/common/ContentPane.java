package service.gui.common;

import service.functionnal.anemo.service.AnemoService;
import service.gui.anemometre.PageAnemo;
import service.gui.anemometre.ui.MaterialColor;
import service.gui.anemometre.ui.WrapLayout;
import service.gui.commercial.MainOffer;
import service.gui.map.MainMap;
import service.gui.pann.MainPanel;
import service.gui.tools.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContentPane extends JPanel {
        private final JFrame parentFame;
        
    public ContentPane(JFrame parentFrame) {
            this.parentFame = parentFrame;
            this.setLayout(new GridLayout(2,2));
            JButton jButton = new JButton("Plan");
            jButton.setForeground(Color.WHITE);
            jButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            MainMap map = null;
                            try {
                                    new MainMap();
                            } catch (Exception ex) {
                                    ex.printStackTrace();
                            }
                            parentFrame.setVisible(false);

                    }
            });
            jButton.setBackground(MaterialColor.GREEN_500);
            jButton.setBorderPainted(false);
            jButton.setOpaque(true);
            jButton.setForeground(Color.WHITE);
            JPanel jPanel = new JPanel(new BorderLayout(20,20));
            JPanel jPanel1 =new JPanel(new GridLayout(2,1,0,0));
            JLabel jLabel = new JLabel("ANEMOMETRE");
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setForeground(MaterialColor.FULLWHITE);
            jPanel.add(jLabel,BorderLayout.NORTH);
            AnemoService anemoService = new AnemoService();
            int s = anemoService.getNumberAnemoConfigured();
            JLabel jlabel1 = new JLabel("Anèmos Configurés:  "+ String.valueOf(s));
            jlabel1.setForeground(MaterialColor.FULLWHITE);
            int sa = anemoService.getNumberNoConfigured();
            JLabel jLabel2 = new JLabel("Anémos Non Configurés:  " +String.valueOf(sa));
            jLabel2.setForeground(MaterialColor.FULLWHITE);
            jlabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jPanel1.add(jlabel1);
            jPanel1.add(jLabel2);
            jPanel.add(jPanel1,BorderLayout.CENTER);
            jPanel.setBackground(MaterialColor.BLUE_700);
            jPanel1.setBackground(MaterialColor.BLUE_700);
            jPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {

                            new PageAnemo();
                            //for testing the best
                           // parentFrame.dispose();
                            parentFrame.setVisible(false);
                    }
            });

           /* JButton jButton1 = new JButton("Anemo");
            jButton1.setBackground(MaterialColor.BLUE_700);
            jButton1.setBorderPainted(false);
            jButton1.setOpaque(true);
            jButton1.setForeground(Color.WHITE);
            jButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            PageAnemo pageAnemo = new PageAnemo();
                            pageAnemo.setVisible(true);
                            parentFrame.setVisible(false);
                    }
            });*/
            JButton jButton2 = new JButton("Solaire");
            jButton2.setBackground(MaterialColor.ORANGE_500);
            jButton2.setBorderPainted(false);
            jButton2.setOpaque(true);
            jButton2.setForeground(Color.WHITE);
            jButton2.addActionListener(e -> {
                    MainPanel mainPanel = new MainPanel();
                    mainPanel.setVisible(true);
                    parentFrame.setVisible(false);
            });
            JButton jButton3 = new JButton("COMMERCIAL");
            jButton3.setBackground(MaterialColor.BROWN_500);
            jButton3.setBorderPainted(false);
            jButton3.setOpaque(true);
            jButton3.setForeground(Color.WHITE);
            jButton3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            MainOffer mainOfferr = new MainOffer();
                            mainOfferr.setVisible(true);
                            parentFrame.setVisible(false);
                    }
            });
            this.add(jButton);
            //this.add(jButton1);
            this.add(jPanel);
            this.add(jButton2);
            this.add(jButton3);



    }
}
