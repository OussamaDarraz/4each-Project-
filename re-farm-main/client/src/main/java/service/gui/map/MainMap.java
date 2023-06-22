package service.gui.map;

import service.functionnal.map.methods.GetMapObject;
import service.functionnal.map.objects.MapObject;
import service.gui.common.LeftMenu;
import service.gui.map.bloc.BlocMap;
import service.gui.map.bloc.BlocMapInfo;
import service.gui.map.bloc.BlocMapTable;
import service.gui.tools.Size;
import service.network.Client;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMap {
    protected static Client client;
    private BlocMap map;
    private BlocMapInfo info;
    private BlocMapTable table;

    public MainMap() throws Exception {
        /*** Creating the window for the map ***/
        client = new Client();
        JFrame f = new JFrame();
        f.setSize(Size.winWidth(), Size.winHeight());

        /*** Adding the Menu to the panel ***/
        LeftMenu leftMenu = new LeftMenu(f);
        f.add(leftMenu, BorderLayout.WEST);

        /*** Adding the content page ***/
        JPanel content = new JPanel();
        List<MapObject> mapObjList = GetMapObject.run(client, -1);

        // Top Bloc of the map page
        info = new BlocMapInfo();
        content.add(info, BorderLayout.NORTH);

        // Middle bloc of the map page, the map
        map = new BlocMap(mapObjList, info, false, client, -1);
        content.add(map, BorderLayout.CENTER);

        // Last bloc of the map page, the table
        table = new BlocMapTable(mapObjList, client, map);
        content.add(table, BorderLayout.SOUTH);

        content.setVisible(true);
        f.add(content, BorderLayout.CENTER);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
