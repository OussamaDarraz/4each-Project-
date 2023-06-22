package service.gui.commercial;


import service.functionnal.commercial.ShowOfferAll;
import service.functionnal.commercial.ShowOfferDelete;
import service.functionnal.commercial.ShowOfferSearch;
import service.network.Client;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;


public class ShowOffer extends JFrame {

    ConfigOffer cofg= new ConfigOffer();
    /**
     *
     */
    private static final long serialVersionUID;

    static {
        serialVersionUID = -6109019363266837112L;
    }

    private JPanel contentPane;
    private JTextField textField;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    Client client = new Client();
    //private static HashMap<Integer, String[]> hashMap;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ShowOffer frame = new ShowOffer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ShowOffer() {
        initComponent();
    }

    private void initComponent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1106, 596);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pane = new JPanel();
        pane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pane.setBounds(60, 22, 852, 37);
        contentPane.add(pane);
        pane.setLayout(null);

        JLabel lab1 = new JLabel("R\u00E9capitulatif des Offres");
        lab1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lab1.setBounds(256, 11, 295, 26);
        pane.add(lab1);

        JPanel pane1 = new JPanel();
        pane1.setBorder(new LineBorder(new Color(0, 0, 0), 4));
        pane1.setBounds(10, 97, 935, 430);
        contentPane.add(pane1);
        pane1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        panel_2.setBounds(950, 135, 141, 392);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        JButton btn1 = new JButton("Afficher");
        btn1.addActionListener(e -> {
            ArrayList<String[]> data = null;
            try {
                data = ShowOfferAll.run(client);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Affichage Impossible");
            }
            if (data != null) {
                for (String[] datum : data) {
                    model.addRow(datum);
                }
                JOptionPane.showMessageDialog(null,"Affichage réussie"
                );
            }

        });

        btn1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn1.setBounds(15, 132, 115, 52);
        panel_2.add(btn1);

        JButton btn2 = new JButton("Supprimer");
        btn2.addActionListener(e -> {
            try {
                int SelectedRowIndex = table.getSelectedRow();

                if (table.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null, "veuillez indiquer la ligne à supprimer, Merci!");
                }else {
                    String s = table.getValueAt(SelectedRowIndex, 0).toString();
                    ShowOfferDelete.rm1(s);
                   String s1= table.getValueAt(SelectedRowIndex,4).toString();
                    ShowOfferDelete.rm2(s1);
                    String s2= table.getValueAt(SelectedRowIndex,1).toString();
                    ShowOfferDelete.rm3(s2);
                    model.removeRow(SelectedRowIndex);
                    JOptionPane.showMessageDialog(null, "Suppression réussie");
                }
            }catch(Exception del)
            {
                JOptionPane.showMessageDialog(null, "Suppression Impossible");}

        });
        btn2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn2.setBounds(15, 230, 115, 52);
        panel_2.add(btn2);

        JButton btn3 = new JButton("Rechercher");
        btn3.addActionListener(e -> {
                    // Searching data & shwowing according to text written
                    String seekname = textField.getText();
                    // removing previous rows in the table
                    int r = table.getRowCount();
                    while (r-- > 0) {
                        model.removeRow(r);
                    }
                    //getting data from database according to seek-name
                    ArrayList<String[]> datom = null;

                    if (seekname.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Paramètre vide");
                    } else {
                        try {
                            datom = ShowOfferSearch.sortSeek(client, seekname);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Récup* de donnée impossible");
                        }
                        for (String[] diatom : datom)

                            model.addRow(diatom);

                    }
                });

            btn3.setBounds(15, 38, 115, 52);
        panel_2.add(btn3);
        btn3.setFont(new Font("Tahoma", Font.BOLD, 13));

        JButton btn4 = new JButton("Imprimer");
        btn4.addActionListener(event -> {
            MessageFormat header = new MessageFormat("Sommaire général des offres");

            MessageFormat footer = new MessageFormat("Refarm: 100% Bio & Frais");

            try {
                if (!(table.getRowCount() == 0)) {
                    table.print(JTable.PrintMode.FIT_WIDTH, header ,footer);
                }else{
                    JOptionPane.showMessageDialog (null, "Impression impossible pour tableau vide !");
                }
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(null,"Impression impossible" +e.getMessage());
            }
        });
        btn4.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn4.setBounds(15, 330, 115, 46);
        panel_2.add(btn4);

        textField = new JTextField();
        textField.setBounds(950, 92, 141, 20);
        contentPane.add(textField);
        textField.setColumns(10);


        scrollPane = new JScrollPane();
        scrollPane.setBounds(10,6,914,402);
        pane1.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(176,196,222));
        table.setBounds(10, 11, 936, 381);
        model = new DefaultTableModel();
        Object [] column = {"Référence","Nom Produit","Quantité Poids","Catégorie","Type Client","Coefficient","Date Création","Date Ligne","Date Expiration","Prix Final"};

        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);


        JLabel lblsearch = new JLabel(" reference");
        lblsearch.setFont(new Font("Tahoma", Font.ITALIC, 18));
        lblsearch.setBounds(970, 55, 94, 22);
        contentPane.add(lblsearch);

        JButton btnfd = new JButton("retour");
        btnfd.addActionListener(e -> {
            this.setVisible(false);
            dispose();
        });
        btnfd.setBounds(448, 536, 89, 23);
        contentPane.add(btnfd);

    }
}
