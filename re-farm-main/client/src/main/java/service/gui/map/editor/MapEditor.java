package service.gui.map.editor;

import service.functionnal.map.objects.MapObject;
import service.gui.map.bloc.BlocMap;
import service.network.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MapEditor extends JFrame {
    JPanel panel;

    public MapEditor(List<MapObject> mObjList, Client client, int id) {
        setName("Editez la carte");
        setSize(new Dimension(400, 400));
        panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        // Calls the map block that allows you to display the map in the editing mode
        add(new BlocMap(mObjList, null, true, client, id));
        setVisible(true);
    }
}
