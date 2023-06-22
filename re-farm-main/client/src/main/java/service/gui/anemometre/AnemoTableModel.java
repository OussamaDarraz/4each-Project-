package service.gui.anemometre;

import service.functionnal.anemo.domain.AnemometreValue;
import service.functionnal.anemo.service.AnemoService;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AnemoTableModel extends AbstractTableModel {
    private final static String [] columns =  new String[]{"Position","Vitesse","Direction","Date Et Heure"};
    private final static AnemoService anemoService = new AnemoService();
    private final List<AnemometreValue> values;

    public AnemoTableModel(String label) {
        this.values = anemoService.getAllAnemoValueByLabel(label);
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
        AnemometreValue value = values.get(rowIndex);
        if(columnIndex == 0) return value.getPosition();
        if(columnIndex == 1) return value.getSpeed();
        if(columnIndex == 2) return value.getDirection();
        else return value.getTimedate();
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    public void refreshData(String newLabel) {
        values.clear();
        values.addAll(anemoService.getAllAnemoValueByLabel(newLabel));
    }
}