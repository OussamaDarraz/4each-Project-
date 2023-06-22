package service.gui.anemometre;

import service.functionnal.anemo.service.AnemoService;
import service.gui.anemometre.ui.MaterialColor;
import service.gui.anemometre.ui.SelectOptions;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SecondPanelConsult extends JPanel {
    public SecondPanelConsult() {
        AnemoService anemoService = new AnemoService();

        setBackground(MaterialColor.FULLWHITE);


        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


        this.setLayout(new BorderLayout(20, 20));
        this.add(new JLabel("CONSULTATION", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel jPanel = new JPanel(new BorderLayout(20, 20));
        JPanel jPanelpetite = new JPanel(new GridLayout(1, 2));
        this.add(jPanel, BorderLayout.CENTER);
        jPanelpetite.add(new JLabel("Anémomètre"));
        JComboBox<String> jComboBox = new SelectOptions<>();
        jPanelpetite.add(jComboBox);

        jPanel.setBackground(MaterialColor.FULLWHITE);
        jPanelpetite.setBackground(MaterialColor.FULLWHITE);


        for (String label : anemoService.getAllObjectLabel()) {
            jComboBox.addItem(label);
        }

        AnemoTableModel anemoTableModel = new AnemoTableModel(String.valueOf(jComboBox.getSelectedItem()));
        JTable jTable = new JTable(anemoTableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? MaterialColor.GREY_300 : MaterialColor.GREY_100);
                }

                return c;
            }
        };
        jTable.setFillsViewportHeight(true);
        jTable.setRowSelectionAllowed(false);
        JTableHeader header = jTable.getTableHeader();
        header.setBackground(MaterialColor.GREY_500);
        header.setForeground(MaterialColor.FULLWHITE);
        JScrollPane scroll = new JScrollPane(jTable);
        jPanel.add(scroll, BorderLayout.CENTER);
        jTable.setRowHeight(50);
        scroll.setBackground(MaterialColor.GREY_300);
        scroll.getVerticalScrollBar().setBackground(Color.LIGHT_GRAY);
        scroll.getHorizontalScrollBar().setBackground(Color.LIGHT_GRAY);
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String newLabel = String.valueOf(jComboBox.getSelectedItem());
                anemoTableModel.refreshData(newLabel);
                jTable.setModel(anemoTableModel);
                jTable.invalidate();
                jTable.repaint();
                jTable.revalidate();
            }
        });


        jPanel.add(jPanelpetite, BorderLayout.NORTH);

        setAlignmentY(Component.CENTER_ALIGNMENT);
    }
}
