package business.map;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMapObject extends TemplateBusiness {
    public static Response run(Request req) {
        try {
            ResultSet rs;
            PreparedStatement st;
            if (req.getParam("id") != null) {
                st = req.co.prepareStatement("SELECT o.obj_id AS id, o.obj_label AS label, o.obj_desc AS desc, o.obj_type AS type, p.x AS x, p.y AS y, p.point_order AS order\n" +
                        "FROM t_map_object o\n" +
                        "LEFT JOIN t_map_position p\n" +
                        "on o.obj_id = p.obj_id" +
                        "WHERE o.obj_id = ?"+
                        "ORDER BY p.point_order");
                st.setInt(1, Integer.parseInt(req.getParam("id")));
            } else {
                st = req.co.prepareStatement("SELECT o.obj_id AS id, o.obj_label AS label, o.obj_desc AS desc, o.obj_type AS type, p.x AS x, p.y AS y, p.point_order AS order\n" +
                        "FROM t_map_object o\n" +
                        "LEFT JOIN t_map_position p\n" +
                        "on o.obj_id = p.obj_id "+
                        "ORDER BY id, p.point_order, obj_type");
            }
            rs = st.executeQuery();
            Response res = new Response("succes");
            res.setMessage("La récupération de(s) objet(s) à été fait avec succés.");
            res.dataSetToArray(rs);
            rs.close();
            st.close();
            return res;
        } catch (Exception e) {
            Response res = new Response("error");
            res.setMessage("Erreur lors de la récupération de l'objet dans la carte.");
            return res;
        }
    }
}
