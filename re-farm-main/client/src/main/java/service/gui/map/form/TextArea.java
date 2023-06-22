package service.gui.map.form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextArea {
    private JPanel panel;
    private String value;
    private JTextArea valueView;

    public TextArea(String label, String value) {
        panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(10, 0, 10, 0)));

        JLabel labelView = new JLabel(label);
        labelView.setBorder(new EmptyBorder(new Insets(0, 0, 0, 20)));
        panel.add(labelView);

        valueView = new JTextArea(value);
        valueView.setMaximumSize(new Dimension(400, 100));
        panel.add(valueView);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getValue() {
        return valueView.getText();
    }

    public void setValue(String value) {
        this.value = value;
    }
}
