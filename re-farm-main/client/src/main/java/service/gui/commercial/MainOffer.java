package service.gui.commercial;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serial;

public class MainOffer extends JFrame {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    CreateOffer coff = new CreateOffer();
    ConfigOffer foff=new ConfigOffer();
    ShowOffer soff = new ShowOffer();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainOffer frame = new MainOffer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainOffer() {
        initComponent();

        coff.setVisible(false);
        foff.setVisible(false);
        soff.setVisible(false);
    }


    private void initComponent() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 781, 523);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(102, 102, 153));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(153, 153, 153));
        panel.setForeground(new Color(169, 169, 169));
        panel.setBounds(205, 46, 305, 398);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btn1 = new JButton("Cr\u00E9er");
        btn1.setBackground(new Color(102, 102, 102));
        btn1.setBounds(49, 56, 221, 43);
        panel.add(btn1);
        btn1.setFont(new Font("Tahoma", Font.BOLD, 22));

        JButton btn2 = new JButton("Sp\u00E9cifier");
        btn2.setBackground(new Color(102, 102, 102));
        btn2.setBounds(49, 180, 221, 48);
        panel.add(btn2);
        btn2.addActionListener(e -> {
            foff.setVisible(true);
            coff.setVisible(false);
            soff.setVisible(false);
            foff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
        btn2.setFont(new Font("Tahoma", Font.BOLD, 22));

        JButton btn3 = new JButton("Récapitulatif");
        btn3.setBackground(new Color(102, 102, 102));
        btn3.setBounds(49, 314, 221, 43);
        panel.add(btn3);
        btn3.addActionListener(e -> {
            coff.setVisible(false);
            foff.setVisible(false);
            soff.setVisible(true);
            soff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
        btn3.setFont(new Font("Tahoma", Font.BOLD, 22));
        btn1.addActionListener(e -> {
            coff.setVisible(true);
            foff.setVisible(false);
            soff.setVisible(false);
            coff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }
}

