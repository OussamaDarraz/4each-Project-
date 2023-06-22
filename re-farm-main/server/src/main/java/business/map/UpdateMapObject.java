package business.map;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateMapObject extends TemplateBusiness {
    public static Response run(Request req) {
        try {
            PreparedStatement st = req.co.prepareStatement("UPDATE t_map_object SET obj_label = ?, obj_desc = ?, obj_type = ? WHERE obj_id = ?");
            st.setString(1, req.getParam("label"));
            st.setString(2, req.getParam("descr"));
            st.setString(3, req.getParam("type"));
            st.setInt(4, Integer.parseInt(req.getParam("id")));
            st.executeUpdate();
            Response res = new Response("succes");
            res.setMessage("Cet élément à bien été modifié.");
            st.close();
            return res;
        } catch (SQLException sqle) {
            Response res = new Response("error");
            res.setMessage("Erreur lors de la modification dans la base de donnée.");
            return res;
        }
    }
}
