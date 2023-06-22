package service.gui.commercial;

import service.functionnal.commercial.CreateOfferInsert;
import service.network.Client;
import service.network.Response;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOffer extends JFrame {

    Client client = new Client();
    ConfigOffer cofg= new ConfigOffer();
    /**
     *
     */

    private JPanel contentPane;
    private JTextField txt1, txt4;

    private JLabel labl1;
    private JLabel labl2;
    private JLabel labl3;

    private JComboBox<String> cbBox1;
    private JComboBox<String> cbBox2;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CreateOffer frame = new CreateOffer();
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public CreateOffer() {

        initialComponents();
    }

    private void initialComponents() {

       //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 926, 514);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setBackground(new Color(102, 102, 102));

        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(146, 29, 540, 45);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(panel);

        JLabel titre = new JLabel("Les informations principales de l'offre ");
        titre.setVerticalAlignment(SwingConstants.BOTTOM);
        titre.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(titre);

        labl1 = new JLabel("R\u00E9férence");
        labl1.setBounds(159, 119, 88, 29);
        labl1.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(labl1);

        labl2 = new JLabel("Nom Produit");
        labl2.setBounds(159, 200, 126, 23);
        labl2.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(labl2);

        labl3 = new JLabel("Quantit\u00E9\\Poids(Kg)");
        labl3.setBounds(118, 282, 167, 26);
        labl3.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(labl3);

        JLabel labl4 = new JLabel("Date Création");
        labl4.setBounds(646, 118, 126, 31);
        labl4.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(labl4);

        txt1 = new JTextField();
        txt1.setBounds(304, 121, 239, 29);
        contentPane.add(txt1);
        txt1.setColumns(10);

        JComboBox cbBox3 = new JComboBox();
        cbBox3.setBounds(304, 279, 239, 29);
        cbBox3.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "25"}));
        contentPane.add(cbBox3);

        txt4 = new JTextField();
        txt4.setBounds(782, 118, 77, 34);
        txt4.setColumns(10);
        contentPane.add(txt4);
        txt4.setText(now());


        final JButton btn1 = new JButton("Valider");
        btn1.setBounds(361, 412, 115, 31);

        btn1.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Sûr? Confirmez la validation?", "Création",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    if(txt1.getText().isEmpty() ){
                        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
                    } else if ((containsDigit(txt1.getText())) ){
                       JOptionPane.showMessageDialog(null, "Veuillez entrer une chaîne de caractère");
                    } else {

                        Response res = CreateOfferInsert.define1(client, (String)cbBox2.getSelectedItem(), Integer.parseInt((String) cbBox3.getSelectedItem()));

                        CreateOfferInsert.define2(client, txt1.getText(), (String) cbBox1.getSelectedItem(), txt4.getText(), res.getMessage());

                        JOptionPane.showMessageDialog(null, "Offre créée");
                    }

                    } catch (IOException ex) {
                    ex.getMessage();
                }

            }else {
                JOptionPane.showMessageDialog(null, "Offre abandonnée");
            }
        });
        btn1.setFont(new Font("Tahoma", Font.BOLD, 16));
        contentPane.add(btn1);

        JLabel labl5 = new JLabel("Cat\u00E9gorie ");
        labl5.setFont(new Font("Tahoma", Font.BOLD, 16));
        labl5.setBounds(159, 354, 106, 23);
        contentPane.add(labl5);

        cbBox1 = new JComboBox<>();

        cbBox1.setFont(new Font("Tahoma", Font.BOLD, 16));
        cbBox1.setModel(new DefaultComboBoxModel<>(new String[]{"V\u00E9g\u00E9tal", "Fruit", "Animal"}));

        cbBox1.setBounds(304, 351, 239, 29);
        contentPane.add(cbBox1);

        cbBox2 = new JComboBox<>();
        cbBox2.setModel(new DefaultComboBoxModel<>(new String[]{"Concombre", "Tomate", "Pomme", "Raisin", "Chou", "Oignon", "Epinard", "Brocoli", "Aubergine", "Echalote", "Courgette","Carotte","lentille","Soja"}));

        cbBox2.setBounds(304, 202, 239, 29);
        contentPane.add(cbBox2);

        JButton btnP = new JButton("<>");
        btnP.setForeground(new Color(192, 192, 192));
        btnP.addActionListener(e ->  {
            this.setVisible(false);
           dispose();
           cofg.setVisible(true);

        });
        btnP.setBounds(395, 454, 56, 23);


        contentPane.add(btnP);
    }

    private static String now() {

            SimpleDateFormat format;
            Date journald = new Date();

            format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(journald);

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
