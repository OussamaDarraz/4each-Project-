package service.gui.pann;


import service.gui.pann.utils.SimtoObject;
import service.gui.pann.utils.Simulation;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SimulTableModel extends AbstractTableModel {
    private final static String [] columns =  new String[]{"X","Y","Facteur_d'ensoleillement","energie génerée"};
    private final List<SimtoObject> values;

    public SimulTableModel(List<SimtoObject> values) {
        this.values = values;
    }


    @Override
    public int getRowCount() {
        return values.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimtoObject value = values.get(rowIndex);
        if(columnIndex == 0) return value.getX();
        if(columnIndex == 1) return value.getY();
        if(columnIndex == 2) return value.getNiveau_ensoleillement();
        else  return value.getEnergy_generated();

    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void refreshData(List<SimtoObject> valuesa) {
        values.clear();
        valuesa = values;
    }

}