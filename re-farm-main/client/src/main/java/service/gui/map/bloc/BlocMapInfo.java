package service.gui.map.bloc;

import service.functionnal.map.objects.MapObject;
import service.gui.anemometre.PageAnemo;
import service.gui.common.Menu;
import service.gui.map.editor.ObjectEditor;
import service.gui.tools.Alert;
import service.gui.tools.Size;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlocMapInfo extends JPanel {
    private JLabel label;
    private JLabel descr;
    private JLabel type;
    private JButton btn;

    public BlocMapInfo() {
        setPreferredSize(new Dimension(Size.widthPercent(80), Size.heightPercent(5)));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));

        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(boxlayout);

        label = new JLabel();
        descr = new JLabel();
        type = new JLabel();
        btn = new JButton();

        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO je sais pas
            }
        });


        addValues("Cliquez sur un objet de la carte pour voir les informations.", "", "", -1);
        setVisible(true);
    }

    public void addValues(String labelTxt, String descrTxt, String typeTxt, int id) {
        label.setText(labelTxt);
        label.setFont(new Font("Sans Serif", Font.BOLD, 12));
        label.setBorder(new EmptyBorder(new Insets(0, 10, 0, 20)));
        add(label);

        descr.setText(descrTxt);
        descr.setFont(new Font("Sans Serif", Font.ITALIC, 12));
        descr.setBorder(new EmptyBorder(new Insets(0, 0, 0, 20)));
        add(descr);

        type.setText(typeTxt);
        type.setBorder(new EmptyBorder(new Insets(0, 0, 0, 20)));
        add(type);

        if (id > -1) {
            btn.setText("Ouvrir");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PageAnemo();
                }
            });
            add(btn);
            btn.setVisible(true);
        } else {
            btn.setVisible(false);
        }


    }
}
