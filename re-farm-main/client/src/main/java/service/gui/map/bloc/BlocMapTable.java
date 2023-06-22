package service.gui.map.bloc;

import service.functionnal.map.methods.DeleteMapObject;
import service.functionnal.map.methods.GetMapObject;
import service.functionnal.map.objects.MapObject;
import service.gui.map.editor.ObjectEditor;
import service.gui.tools.Alert;
import service.gui.tools.Confirm;
import service.gui.tools.ErrorMessage;
import service.gui.tools.Size;
import service.network.Client;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class BlocMapTable extends JPanel {
    private final String[] columns = new String[] {"ID", "Label", "Type", "Desc"};
    private int[] columnsWidth = { 50, 200, 200, 400 };
    private int selectedLine = -1;
    private List<MapObject> mObjList;
    private Client client;
    private BlocMap blocMap;
    private JTable table;

    public BlocMapTable(List<MapObject> mObjList, Client client, BlocMap blocMap) {
        this.blocMap = blocMap;
        this.client = client;
        this.mObjList = mObjList;
        setPreferredSize(new Dimension(Size.widthPercent(80), Size.heightPercent(30)));

        // Creation of the table
        Object[][] data = new Object[mObjList.size()][columns.length];
        int i = 0;
        for (MapObject mObj : mObjList) {
            data[i][0] = mObj.getId();
            data[i][1] = mObj.getLabel();
            data[i][2] = mObj.getType();
            data[i][3] = mObj.getDescription();
            i++;
        }
        table = new JTable(data, columns);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedLine = table.getSelectedRow();
            }
        });

        // Size of the table
        int j = 0;
        for (int width : columnsWidth) {
            TableColumn column = table.getColumnModel().getColumn(j++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }

        // Creation of the scroll pane
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(Size.widthPercent(80), Size.heightPercent(20)));
        table.setFillsViewportHeight(true);

        JLabel labelHead = new JLabel("Capteurs, Champs et Batiments", SwingConstants.LEFT);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));

        add(labelHead,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);

        JButton btnDel = deleteBtn();
        JButton btnUpd = updateBtn();
        JButton btnIn = insertBtn();
        JButton btnRefresh = refreshBtn();

        add(btnDel);
        add(btnUpd);
        add(btnIn);
        add(btnRefresh);
    }

    private void refresh() {
        try {
            mObjList = GetMapObject.run(client, -1);
            // table.removeAll();
            //for(int i = table.getRowCount()-1; i>0 ; i--) table.remove(i);

            blocMap.refreshMap();
        } catch (IOException ex) {
            new Alert(ErrorMessage.TECH_PROBLEM);
        }

    }

    private JButton updateBtn() {
        JButton btnUp = new JButton("Modifier");
        btnUp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedLine > -1) {
                    MapObject current = mObjList.get(selectedLine);
                    new ObjectEditor(current.getId(), current.getLabel(), current.getDescription(), current.getType(), client, mObjList);
                    refresh();
                } else {
                    new Alert("Aucune ligne séléctionnée");
                }
            }
        });
        refresh();
        return btnUp;
    }

    private JButton deleteBtn() {
        JButton btnDel = new JButton("Supprimer");
        btnDel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedLine > -1) {
                    try {
                        MapObject current = mObjList.get(selectedLine);
                        if(Confirm.run("Etes vous sur de vouloir supprimer cet objet ?")) {
                            String msg = DeleteMapObject.run(client, current.getId());
                            new Alert(msg);
                            refresh();
                        }
                    } catch (IOException ex) {
                        new Alert("Problème avec le serveur.");
                    }
                } else {
                    new Alert("Aucune ligne séléctionnée");
                }
            }
        });

        return btnDel;
    }

    private JButton insertBtn() {
        JButton btnIn = new JButton("Nouveau");
        btnIn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ObjectEditor(mObjList, client);
                try {
                    mObjList = GetMapObject.run(client, -1);
                    refresh();
                } catch (IOException ex) {
                    new Alert(ErrorMessage.INSERT_DB_PROBLEM);
                }
            }
        });

        return btnIn;
    }

    private JButton refreshBtn() {
        JButton btnRefresh = new JButton("Actualiser");
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        return btnRefresh;
    }


}

