package service.gui.map.form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Selector {
    private JPanel panel;
    private String[] vals;
    private JComboBox valueView;

    public Selector(String label, String[] vals) {
        this.vals = vals;
        panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        JLabel labelView = new JLabel(label);
        labelView.setBorder(new EmptyBorder(new Insets(0, 0, 0, 20)));
        panel.add(labelView);

        valueView = new JComboBox(vals);
        valueView.setMaximumSize(new Dimension(400, 30));

        panel.add(valueView);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getValue() {
        return vals[valueView.getSelectedIndex()];
    }

    public void setValue(String value) {
        valueView.setSelectedItem(value);
    }

}
