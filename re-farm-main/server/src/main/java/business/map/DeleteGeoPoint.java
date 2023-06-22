package business.map;

import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteGeoPoint {
    public static Response run(Request req) {
        try {
            PreparedStatement st = req.co.prepareStatement("DELETE FROM t_map_position WHERE obj_id = ?");
            st.setInt(1, Integer.parseInt(req.getParam("id")));
            st.executeUpdate();
            Response res = new Response("succes");
            res.setMessage("Position supprimée.");
            st.close();
            return res;
        } catch (SQLException sqle) {
            Response res = new Response("error");
            res.setMessage("Reeur lors de la suppréssion du point dans la base de donnée.");
            return res;
        }
    }
}
