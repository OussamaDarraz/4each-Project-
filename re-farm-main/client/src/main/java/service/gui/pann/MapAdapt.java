package service.gui.pann;

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

public class MapAdapt {
    protected static Client client;
    private BlocMap map;
    private BlocMapInfo info;

    public MapAdapt() throws Exception {
        /*** Creating the window for the map ***/
        client = new Client();
        JFrame f = new JFrame();
        f.setSize(Size.winWidth(), Size.winHeight());

        /*** Adding the content page ***/
        JPanel content = new JPanel();
        List<MapObject> mapObjList = GetMapObject.run(client, -1);

        // Top Bloc of the map page
        info = new BlocMapInfo();
        content.add(info, BorderLayout.NORTH);

        // Middle bloc of the map page, the map
        map = new BlocMap(mapObjList, info, false, client, -1);
        content.add(map, BorderLayout.CENTER);

        content.setVisible(true);
        f.add(content, BorderLayout.CENTER);

        f.setVisible(true);
    }
}
