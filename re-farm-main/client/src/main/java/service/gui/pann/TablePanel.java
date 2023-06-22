package service.gui.pann;

import service.functionnal.anemo.service.AnemoService;
import service.gui.anemometre.ui.MaterialColor;
import service.gui.anemometre.ui.SelectOptions;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablePanel extends JPanel {
    SimulTableModel simulTableModel ;
    public TablePanel ( SimulTableModel simulTableModel ) {
        this.simulTableModel = simulTableModel;
        setBackground(MaterialColor.FULLWHITE);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.setLayout(new BorderLayout(20, 20));
        this.add(new JLabel("CONSULTATION", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel jPanel = new JPanel(new BorderLayout(20, 20));
        JPanel jPanelpetite = new JPanel(new GridLayout(1, 2));
        this.add(jPanel, BorderLayout.CENTER);

        jPanel.setBackground(MaterialColor.FULLWHITE);
        jPanelpetite.setBackground(MaterialColor.FULLWHITE);

        JTable jTable =new JTable(simulTableModel);

        jTable.setFillsViewportHeight(true);
        jTable.setRowSelectionAllowed(false);
        JTableHeader header = jTable.getTableHeader();
        header.setBackground(MaterialColor.GREY_500);
        header.setForeground(MaterialColor.FULLWHITE);
        JScrollPane scroll = new JScrollPane(jTable);
        jPanel.add(scroll, BorderLayout.CENTER);
        jTable.setRowHeight(25);
        scroll.setBackground(MaterialColor.FULLWHITE);

        jPanel.add(jPanelpetite, BorderLayout.NORTH);

        setAlignmentY(Component.CENTER_ALIGNMENT);
    }
}
