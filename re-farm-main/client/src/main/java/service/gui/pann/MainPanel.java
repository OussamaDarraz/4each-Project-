package service.gui.pann;

import service.gui.anemometre.ui.MaterialColor;
import service.gui.common.LeftMenu;
import service.gui.map.bloc.BlocMap;
import service.gui.map.bloc.BlocMapInfo;
import service.gui.pann.utils.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import static service.gui.pann.utils.EnergyAlgo.*;
import static service.gui.pann.utils.EnergyAlgo.EnergyCalculing;

public class MainPanel  extends JFrame {
    public  SimulTableModel  simulTableModel ;
    public ArrayList<SimtoObject> simtoObjectlist ;
    private BlocMap map;
    private BlocMapInfo info;
    public JPanel rs ;
    public JFrame frma ;
    public MapAdapt mapa;



    /**
     * lAUNCH THE MAIN Panel.
     */


    /**
     * INITIALIZE THE MAIN PANNEL
     */
    public MainPanel() {

        initialize();


    }



    /**
     * Initialize The Field For the user to enter the Energy value
     */
    private void initialize() {
        this.setVisible(true);
        setTitle("Re-Farm (Emplacement des panneaux)");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


        JLabel lblNewLabel = new JLabel("\u00C9nergie");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        lblNewLabel.setBounds(416, 116, 121, 50);
        getContentPane().add(lblNewLabel);


        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"kWh"}));
        comboBox.setBounds(741, 131, 65, 22);
        getContentPane().add(comboBox);
        comboBox.setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[]{"Panneaux Monocristallins"}));
        comboBox_1.setBounds(498, 192, 168, 22);
        getContentPane().add(comboBox_1);
        comboBox_1.setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        frma = new JFrame();

        frma.setTitle("Re-Farm (Resultat de la simulation)");
        frma.setBounds(100, 100, 800, 550);
        frma.getContentPane().setLayout(null);



        rs = new JPanel();
        rs.setBounds(0, 0, 774, 412);

        rs.setBackground(MaterialColor.FULLWHITE);
        rs.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        rs.setLayout(new BorderLayout(20, 20));
        rs.add(new JLabel("Details de l'estimation ", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel jPanel = new JPanel(new BorderLayout(20, 20));
        JPanel jPanelpetite = new JPanel(new GridLayout(1, 2));
        rs.add(jPanel, BorderLayout.CENTER);

        jPanel.setBackground(MaterialColor.FULLWHITE);
        jPanelpetite.setBackground(MaterialColor.FULLWHITE);





        JLabel lblNewLabela = new JLabel("Energie totale :");
        lblNewLabela.setBounds(39, 440, 129, 18);
        frma.getContentPane().add(lblNewLabela);

        JLabel lblNewLabel_1 = new JLabel("Nombre de panneaux :");
        lblNewLabel_1.setBounds(39, 469, 152, 20);
        frma.getContentPane().add(lblNewLabel_1);

        JLabel a = new JLabel();
        a.setBounds(187, 439, 143, 20);
        frma.getContentPane().add(a);


        JLabel b = new JLabel();
        b.setBounds(187, 469, 143, 20);
        frma.getContentPane().add(b);




        JComboBox comboBox_un = new JComboBox();
        comboBox_un.setModel(new DefaultComboBoxModel(new String[]{"kWh"}));
        comboBox_un.setBounds(335, 438, 69, 20);
        frma.getContentPane().add(comboBox_un);

        comboBox_un.setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });


        JButton btnNewButton_re = new JButton("Enregistrer");

        frma.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                rs.removeAll();
                btnNewButton_re.setEnabled(true);

            }
        });



        btnNewButton_re.setBounds(645, 438, 117, 23);
        frma.getContentPane().add(btnNewButton_re);

        JTextField textField_a = new JTextField();
        textField_a.setBounds(498, 131, 225, 22);
        getContentPane().add(textField_a);






        JButton btnNewButton = new JButton("Simuler");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simtoObjectlist = new ArrayList<>();

                if (textField_a.getText().isEmpty() || Double.parseDouble(textField_a.getText()) == 0 || Double.parseDouble(textField_a.getText()) < 0.5 )
                {
                    frma.setVisible(true);
                    a.setText("Erreur");
                    b.setText("Erreur");
                    int result2 = JOptionPane.showConfirmDialog(null, "Veuillez entrer une valeur > 0.5 pour simuler", "Attention",
                            JOptionPane.CLOSED_OPTION,
                            JOptionPane.ERROR_MESSAGE);

                    if (result2 == JOptionPane.CLOSED_OPTION) {
                        setVisible(false);
                        frma.setVisible(false);

                    }


                }
                else if ( ( Double.compare(Double.parseDouble(textField_a.getText().toString())  , EnergyChecking.EnergywType())) > 0 )
                {

                    int result = JOptionPane.showConfirmDialog(null, "la carte ne supporte pas ce nombre de panneaux solaires", "Alerte",
                            JOptionPane.CANCEL_OPTION ,
                            JOptionPane.WARNING_MESSAGE);
                    if (result == JOptionPane.CANCEL_OPTION) {

                    } else {

                        frma.setVisible(true);

                        a.setText((EnergyCalculing(Double.valueOf(textField_a.getText()))[0]).toString());
                        b.setText((EnergyCalculing(Integer.valueOf(textField_a.getText()))[1]).toString());
                        double d = Double.parseDouble(textField_a.getText())  ;
                        simtoObjectlist =  Simulation.EnergySimul(d) ;








                    }






                }
                else if ( Double.parseDouble(textField_a.getText().toString()) < 0)
                {
                    int result2 = JOptionPane.showConfirmDialog(null, "Vous avez entrer une valeur négatif", "Attention",
                            JOptionPane.CLOSED_OPTION,
                            JOptionPane.ERROR_MESSAGE);
                    if (result2 == JOptionPane.CLOSED_OPTION) {
                        setVisible(false);
                    }

                }
                else {
                    frma.setVisible(true);

                    a.setText((EnergyCalculing(Double.valueOf(textField_a.getText()))[0]).toString());
                    b.setText(((EnergyCalculing(Double.valueOf(textField_a.getText()))[1])).toString());
                    double d = Double.parseDouble(textField_a.getText());
                    simtoObjectlist =  Simulation.EnergySimul(d) ; //     Double.parseDouble(a.getText())



                }

                displaytable ( );

            }
        });
        btnNewButton.setBounds(517, 636, 133, 59);
        getContentPane().add(btnNewButton);



        btnNewButton_re.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Applying.algoapply(Double.parseDouble(textField_a.getText()));
                try {
                    MapAdapt ad = new MapAdapt();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                btnNewButton_re.setEnabled(false);


            }
        });





        JLabel lblTypeDePaneau = new JLabel("Type de panneau");
        lblTypeDePaneau.setForeground(Color.DARK_GRAY);
        lblTypeDePaneau.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
        lblTypeDePaneau.setBackground(Color.LIGHT_GRAY);
        lblTypeDePaneau.setBounds(367, 177, 121, 50);
        getContentPane().add(lblTypeDePaneau);


        JLabel lblNewLabel_3 = new JLabel("Panneaux Solaires");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblNewLabel_3.setBounds(496, -15, 325, 85);
        getContentPane().add(lblNewLabel_3);

        LeftMenu leftMenu = new LeftMenu(this);
        leftMenu.setBounds(0,30,110 ,710);
        getContentPane().add(leftMenu);

        JLabel lblEu = new JLabel("\u00C9nergie Maximale");
        lblEu.setForeground(Color.DARK_GRAY);
        lblEu.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 11));
        lblEu.setBackground(Color.LIGHT_GRAY);
        lblEu.setBounds(323, 239, 183, 50);
        getContentPane().add(lblEu);

        JLabel lblNewLabelu = new JLabel("               --1.2--");
        lblNewLabelu.setBounds(498, 258, 168, 14);
        getContentPane().add(lblNewLabelu);





        JLabel lblNewLabel_kw = new JLabel("kWh");
        lblNewLabel_kw.setBounds(687, 250, 56, 22);
        getContentPane().add(lblNewLabel_kw);








    }

    public void displaytable (  )
    {
        simulTableModel = new SimulTableModel(simtoObjectlist);
        JTable jTable =new JTable(simulTableModel);
        jTable.setFillsViewportHeight(true);
        jTable.setRowSelectionAllowed(false);
        JTableHeader header = jTable.getTableHeader();
        header.setBackground(MaterialColor.GREY_500);
        header.setForeground(MaterialColor.FULLWHITE);
        JScrollPane scroll = new JScrollPane(jTable);
        rs.add(scroll, BorderLayout.CENTER);
        jTable.setRowHeight(25);
        scroll.setBackground(MaterialColor.FULLWHITE);
        rs.setAlignmentY(Component.CENTER_ALIGNMENT);
        rs.updateUI();
        frma.getContentPane().add(rs);


    }

}



