package service.gui.map.editor;

import service.functionnal.map.methods.DeleteGeoPoint;
import service.functionnal.map.methods.GetMapObject;
import service.functionnal.map.methods.PostMapObject;
import service.functionnal.map.methods.UpdateMapObject;
import service.functionnal.map.objects.MapObject;
import service.gui.map.form.Selector;
import service.gui.map.form.TextArea;
import service.gui.map.form.TextInput;
import service.gui.tools.Alert;
import service.network.Client;
import service.network.Response;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ObjectEditor extends JFrame {
    /*** TO USE ***/
    protected boolean updateMode = false;
    private List<MapObject> mapObjectList;

    private int id;
    private TextInput labelView;
    private TextArea descrView;
    private Selector typeView;
    private JLabel statusMsg;

    /*** UTIL ***/
    private String[] types = {"FIELD", "SENSOR", "BUILDING"};
    private JPanel panel;
    private Client client;

    /*** Insert Object ***/
    public ObjectEditor(List<MapObject> mapObjectList, Client client) {
        this.client = client;
        this.mapObjectList = mapObjectList;
        init("Ajouter un objet de la carte");

        labelView = new TextInput("Label", "");
        descrView = new TextArea("Description", "");
        typeView = new Selector("Type", types);
        panel.add(labelView.getPanel());
        panel.add(descrView.getPanel());
        panel.add(typeView.getPanel());

        JButton btn = new JButton("VALIDER");
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateMode) update();
                else insert();
            }
        });

        panel.add(btn);

        statusMsg = new JLabel("");
        panel.add(statusMsg);
        add(panel);
        setVisible(true);
    }

    /*** Update Object ***/
    public ObjectEditor(int id, String label, String descr, String type, Client client, List<MapObject> mapObjectList) {
        this.client = client;
        this.updateMode = true;
        this.id = id;
        init("Editer un objet de la carte");

        labelView = new TextInput("Label", label);
        descrView = new TextArea("Description", descr);
        typeView = new Selector("Type", types);
        typeView.setValue(type);

        panel.add(labelView.getPanel());
        panel.add(descrView.getPanel());
        panel.add(typeView.getPanel());

        JButton btn = okBtn();
        panel.add(btn);

        JButton btnUpdatePos = new JButton("MODIFIER LA POSITION");
        btnUpdatePos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DeleteGeoPoint.run(client, id);
                    new MapEditor(mapObjectList, client, id);
                } catch (IOException ex) {
                    new Alert("Erreur lors de la modification de la position.");
                }
            }
        });

        panel.add(btnUpdatePos);

        statusMsg = new JLabel("", SwingConstants.LEFT);
        panel.add(statusMsg);
        add(panel);
        setVisible(true);
    }

    private void init(String name) {
        setName(name);
        setSize(new Dimension(400, 400));
        panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
    }

    /*** METHODS ***/
    public void insert() {
        try {
            Response res = PostMapObject.run(client, labelView.getValue(), descrView.getValue(), typeView.getValue());

            statusMsg.setText(res.getMessage());
            new MapEditor(mapObjectList, client, res.getDataNode().get(0).path("obj_id").asInt());
        } catch (Exception e) {
            statusMsg.setText("Erreur lors de l'insertion.");
        }
    }

    public void update() {
        try {
            String respMsg = UpdateMapObject.run(client, id,  labelView.getValue(), descrView.getValue(), typeView.getValue());
            statusMsg.setText(respMsg);
        } catch (Exception e) {
            statusMsg.setText("Erreur lors de la modifiaction..");
        }
    }

    private JButton okBtn() {
        JButton btn = new JButton("VALIDER");
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateMode) update();
                else insert();
            }
        });
        return btn;
    }

}

