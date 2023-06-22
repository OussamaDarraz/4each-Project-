package service.gui.map.bloc;

import service.functionnal.map.methods.GetMapObject;
import service.functionnal.map.methods.PostGeoPoint;
import service.functionnal.map.objects.MapObject;
import service.gui.map.objects.BuildingObj;
import service.gui.map.objects.FieldObj;
import service.gui.map.objects.PanelObj;
import service.gui.map.objects.SensorObj;
import service.gui.tools.Alert;
import service.gui.tools.Size;
import service.network.Client;
import service.network.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;

public class BlocMap extends JPanel implements MouseListener {
    private Client client;
    private boolean editMode;
    private List<MapObject> mapObjList;
    private Graphics g;

    private int coefX = 5;
    private int coefY = 4;
    private int newId;

    // Link to bloc info
    BlocMapInfo blocInfo;

    public BlocMap(List<MapObject> mapObjList, BlocMapInfo blocInfo, boolean editMode, Client client, int newId) {
        this.newId = newId;
        this.client = client;
        this.editMode = editMode;
        this.mapObjList = mapObjList;
        this.blocInfo = blocInfo;

        setPreferredSize(new Dimension(Size.widthPercent(80), Size.heightPercent(60)));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        setVisible(true);
        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        this.g = g;
        int width = getWidth();
        int height = getHeight();

        // Adds objects to the map according to their type
        for (MapObject mo : mapObjList) {
            if (mo.getType().equals("FIELD"))    {
                mo.setObj(new FieldObj(g, mo, width, height));
                mo.getObj().addToMap();
            }
            if (mo.getType().equals("SENSOR"))  {
                mo.setObj(new SensorObj(g, mo, width, height));
                mo.getObj().addToMap();
            }
            if (mo.getType().equals("BUILDING"))  {
                mo.setObj(new BuildingObj(g, mo, width, height));
                mo.getObj().addToMap();
            }
            if (mo.getType().equals("PANEL"))  {
                mo.setObj(new PanelObj(g, mo, width, height));
                mo.getObj().addToMap();
            }
        }
    }


    /*** Mouse Listener ***/
    @Override
    public void mouseClicked(MouseEvent e) {
        if (editMode) {
            // Allows you to place objects with the mouse on the map when editing
            try {
                Response res = PostGeoPoint.run(client, newId, setW(e.getX()), setH(e.getY()) );
                refreshMap();
            } catch (IOException ex) {
                new Alert("Prblème lors de l'ajout d'un point.");
            }
        } else {
            // Allows you to display information when you click on an object on the map
            for (MapObject mo : mapObjList) {
                if (mo.getObj().contains(e.getX(), e.getY())) {
                    blocInfo.addValues(mo.getLabel(), mo.getDescription(), mo.getType(), mo.getType().equals("SENSOR") ? mo.getId() : -1);
                }
            }
        }
    }

    // Methods to convert gps points for the database
    protected int setW(int w) {
        float per = (float) w/getWidth();
        return (int) (100 * per);
    }

    protected int setH(int h) {
        float per = (float) h/getHeight();
        return (int) (100 * per);
    }

    public void refreshMap() {
        try {
            mapObjList = null;
            mapObjList = GetMapObject.run(client, -1);
            repaint();
        } catch (IOException e) {
            new Alert("Problème lors de l'actualisation de la carte.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
