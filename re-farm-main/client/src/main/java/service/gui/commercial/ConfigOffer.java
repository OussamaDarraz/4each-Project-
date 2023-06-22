package service.gui.commercial;


import service.functionnal.commercial.SpecOfferAll;
import service.functionnal.commercial.SpecOfferInsert;
import service.functionnal.commercial.SpecOfferUpdate;
import service.functionnal.commercial.operation.ToSum;
import service.network.Client;
import service.network.Response;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class ConfigOffer extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -4609722862030369996L;
    private JPanel contentPane = new JPanel();
    private JTextField txtf1,txtf2,txtf3,txtf4;
    private JComboBox cbBox,cbBox1;
    private JPanel panel,pane1;
    private JLabel labl1,labl3,labl4, labl5,labl6;
    private JRadioButton rbtn1;
    private JPanel panel_2;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    Client client = new Client();
    private LocalDate dt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConfigOffer frame = new ConfigOffer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public ConfigOffer() {
        initComponent();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void initComponent() {
        setBounds(100, 100, 1092, 609);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(265, 11, 556, 39);
        contentPane.add(panel);

        labl1 = new JLabel("Les Informations de sp\u00E9cifications d'une offre  ");
        labl1.setVerticalAlignment(SwingConstants.BOTTOM);
        labl1.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labl1);

        txtf2 = new JTextField();
        txtf2.setBounds(478, 162, 135, 20);
        contentPane.add(txtf2);
        txtf2.setColumns(10);

        cbBox = new JComboBox();
        cbBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        cbBox.setModel(new DefaultComboBoxModel(new String[] {"Public", "Priv\u00E9", "Collectivit\u00E9"}));
        cbBox.setBounds(480, 93, 135, 30);
        contentPane.add(cbBox);

        pane1 = new JPanel();
        pane1.setBorder(new LineBorder(new Color(105, 105, 105), 3));
        pane1.setBounds(45, 61, 988, 189);
        contentPane.add(pane1);
        pane1.setLayout(null);

        JButton btn2 = new JButton("Modifier");
        btn2.addActionListener(e -> {
        try{
            SpecOfferUpdate.rollUp1(client, (String) cbBox.getSelectedItem(), (String) cbBox1.getSelectedItem(), txtf4.getText());

            SpecOfferUpdate.rollUp2(client,txtf1.getText() ,txtf2.getText() ,txtf3.getText(),txtf4.getText());
            JOptionPane.showMessageDialog(null,"Modification réussie, Bravo!");
        }catch(IOException eu2){
            eu2.getMessage();
            JOptionPane.showMessageDialog(null,"Modification échouée, Désolé!");
        }


        });
        btn2.setBounds(452, 147, 118, 29);
        pane1.add(btn2);
        btn2.setFont(new Font("Tahoma", Font.BOLD, 15));

        JButton btn1 = new JButton("D\u00E9finir");
        btn1.addActionListener(e -> {
                  /*  Date date = null;
                    String inputdate = txtf1.getText();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                if (dateFormat.parse(inputdate).isLenient()) {
                    return;
                }


            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Date en ligne invalide");
                        }*/

                        try {
                            if (txtf4.getText().isEmpty() || txtf1.getText().isEmpty() || txtf2.getText().isEmpty() || txtf3.getText().isEmpty() || containsDigit(txtf4.getText())) {
                                JOptionPane.showMessageDialog(null, "Saisir une réference existante , remplir tous les champs et éviter les  digits");
                            } else if (!txtf4.getText().isEmpty() || !txtf1.getText().isEmpty() || !txtf2.getText().isEmpty() || !txtf3.getText().isEmpty()) {

                                Response res = SpecOfferInsert.round1(client, (String) cbBox.getSelectedItem(), (String) cbBox1.getSelectedItem());

                                SpecOfferInsert.round2(client, txtf1.getText(), txtf2.getText(), (Integer.parseInt(txtf3.getText())), Integer.parseInt(res.getMessage()), txtf4.getText());

                                JOptionPane.showMessageDialog(null, "Les propriétés sont bien définies");
                            } else if ((!(txtf1.getText().compareTo(String.valueOf(LocalDate.now())) > 0)) || (!(txtf1.getText().compareTo(String.valueOf(LocalDate.now())) == 0))) {
                                JOptionPane.showMessageDialog(null, "Verifier la date de mise en ligne svp!");
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (Exception io) {
                            io.printStackTrace();
                            JOptionPane.showMessageDialog(null, " Désolé! Les propriétés ne sont pas bien définies");

                        }


                });
        btn1.setBounds(280, 147, 111, 29);
        pane1.add(btn1);
        btn1.setFont(new Font("Tahoma", Font.BOLD, 15));

        JButton btn3 = new JButton("Afficher");
        btn3.addActionListener(e -> {
            ArrayList<String[]> data = null;
            try {
                data = SpecOfferAll.run(client);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            for (String[] datim : data) {
                model.addRow(datim);
            }

        }  );
        btn3.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn3.setBounds(625, 147, 118, 29);
        pane1.add(btn3);

        txtf4 = new JTextField();
        txtf4.setBounds(148, 34, 111, 22);
        pane1.add(txtf4);
        txtf4.setColumns(10);

        labl5 = new JLabel("Prix final");
        labl5.setBounds(655, 97, 88, 29);
        pane1.add(labl5);
        labl5.setFont(new Font("Tahoma", Font.BOLD, 16));

        rbtn1 = new JRadioButton("Calcul");
        rbtn1.addActionListener(e -> {
            try{

               ToSum ca = new ToSum();
               int p= ca.calc(Integer.parseInt(cbBox1.getSelectedItem().toString()));

                txtf3.setText(String.valueOf(p));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        rbtn1.setBounds(862, 100, 89, 23);
        pane1.add(rbtn1);
        rbtn1.setFont(new Font("Tahoma", Font.BOLD, 16));

        labl6 = new JLabel("Coefficient(%)");
        labl6.setBounds(647, 30, 121, 26);
        pane1.add(labl6);
        labl6.setFont(new Font("Tahoma", Font.BOLD, 16));

        txtf3 = new JTextField();
        txtf3.setBounds(780, 100, 60, 27);
        pane1.add(txtf3);
        txtf3.setColumns(6);

        cbBox1 = new JComboBox();
        cbBox1.setBounds(775, 32, 71, 24);
        pane1.add(cbBox1);
        cbBox1.setFont(new Font("Tahoma", Font.BOLD, 14));
        cbBox1.setModel(new DefaultComboBoxModel(new String[] {"5", "10","15"}));

        JLabel labl1_1 = new JLabel("Type Client");
        labl1_1.setBounds(293, 26, 118, 35);
        pane1.add(labl1_1);
        labl1_1.setFont(new Font("Tahoma", Font.BOLD, 16));

        labl4 = new JLabel("R\u00E9f\u00E9rence");
        labl4.setBounds(10, 28, 88, 31);
        pane1.add(labl4);
        labl4.setFont(new Font("Tahoma", Font.BOLD, 16));

        txtf1 = new JTextField();
        txtf1.setBounds(148, 102, 111, 23);
        pane1.add(txtf1);
        txtf1.setColumns(10);

        JLabel labl2_1 = new JLabel("Date ligne");
        labl2_1.setBounds(10, 100, 119, 22);
        pane1.add(labl2_1);
        labl2_1.setFont(new Font("Tahoma", Font.BOLD, 16));


        labl3 = new JLabel("Date expiration");
        labl3.setBounds(290, 100, 135, 22);
        pane1.add(labl3);
        labl3.setFont(new Font("Tahoma", Font.BOLD, 16));

        JRadioButton rbtn2 = new JRadioButton("*");
        rbtn2.addActionListener(e ->  {
           try {
                if (txtf1.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Veuillez indiquer la date de mise en ligne en premier");
                } else {
                    LocalDate dligne=  LocalDate.parse(txtf1.getText());
                    LocalDate dt =dligne.plusDays(20);
                    txtf2.setText(String.valueOf(dt));
                }
            } catch (HeadlessException ex) {
                ex.printStackTrace();
            }
        });
        rbtn2.setVerticalAlignment(SwingConstants.TOP);
        rbtn2.setFont(new Font("Tahoma", Font.BOLD, 16));
        rbtn2.setBounds(589, 97, 35, 23);
        pane1.add(rbtn2);

        panel_2 = new JPanel();
        panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_2.setBounds(21, 261, 1030, 292);
        contentPane.add(panel_2);
        panel_2.setLayout(null);


        scrollPane = new JScrollPane();
        scrollPane.setBounds(10,6,1010,275);
        panel_2.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(176,196,222));
        table.setBounds(10, 11, 936, 381);
        model = new DefaultTableModel();
        Object [] column = {"Référence","Type_Client","Coefficient","Date_en_Ligne","Date_Expiration","Prix_Final"};
        @SuppressWarnings("unused")
        final Object [] row = new Object [6];

        model.setColumnIdentifiers(column);
        table.setModel(model);

        // Get the selected row from JTable and put the data into JTextFields
        table.getSelectionModel().addListSelectionListener(e -> {
            int i = table.getSelectedRow();
            txtf4.setText((String)model.getValueAt(i, 0));
            cbBox.setSelectedItem(model.getValueAt(i, 1));
            cbBox1.setSelectedItem(model.getValueAt(i, 2));
            txtf1.setText((String)model.getValueAt(i,3));
            txtf2.setText((String)model.getValueAt(i,4));
            txtf3.setText((String)model.getValueAt(i,5));

        });

        scrollPane.setViewportView(table);

       JButton btnfd = new JButton("<>");
        btnfd.addActionListener(e ->  {
         this.setVisible(false);
         dispose();
        });
       btnfd.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnfd.setForeground(new Color(192, 192, 192));
        btnfd.setBounds(497, 551, 89, 20);
        contentPane.add(btnfd);

    }
    //  prevent Integer instead of String in textfield
    public boolean containsDigit(String text) {
        // Iterate over every character

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Check whether c is a digit
            if (Character.isDigit(c)) {
                return true;
            }
        }
        // Iterated through the text, no digit found
        return false;
    }

    }

