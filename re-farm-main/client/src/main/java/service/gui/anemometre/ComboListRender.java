package service.gui.anemometre;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ComboListRender extends DefaultListCellRenderer {
    private final static Font f2 = new Font("Tahoma", 0, 14);
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof JComponent)
            return (JComponent) value;

        boolean itemEnabled = !Objects.isNull(value);

        super.getListCellRendererComponent(list, value, index,
                isSelected && itemEnabled, cellHasFocus);

        // Render item as disabled and with different font:
        setEnabled(itemEnabled);
        if(!itemEnabled) setFont(f2);
        setText(!itemEnabled ? "Selectionner un" : value.toString());

        return this;
    }
}
