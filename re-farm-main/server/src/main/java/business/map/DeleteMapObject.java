package business.map;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;

public class DeleteMapObject extends TemplateBusiness {
    public static Response run(Request req) {
        try {
            PreparedStatement st;
            if (req.getParam("id") != null) {
                st = req.co.prepareStatement("call delete_map_object(?)");
                st.setInt(1, Integer.parseInt(req.getParam("id")));
                st.executeUpdate();
                Response res = new Response("succes");
                res.setMessage("L'objet à bien été supprimé de la carte.");
                st.close();
                return res;
            }
        } catch (Exception e) {
            Response res = new Response("error");
            res.setMessage("Erreur lors de la suppression d'un objet dans la base de donnée.");
            return res;
        }
        Response res = new Response("error");
        res.setMessage("Erreur lors de la suppression d'un objet dans la base de donnée. L'id est nul.");
        return res;
    }
}
