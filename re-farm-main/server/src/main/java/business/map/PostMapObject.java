package business.map;

import business.TemplateBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostMapObject extends TemplateBusiness {
    static Logger logger = LoggerFactory.getLogger(PostMapObject.class.getName());
    public static Response run(Request req) {
        try {
            Response res = new Response("succes");
            /*** INSERT ***/
            PreparedStatement st = req.co.prepareStatement("INSERT INTO t_map_object(obj_label, obj_desc, obj_type) VALUES (?, ?, ?)");
            st.setString(1, req.getParam("label"));
            st.setString(2, req.getParam("descr"));
            st.setString(3, req.getParam("type"));
            st.executeUpdate();
            st.close();

            /*** RECUP ID ***/
            PreparedStatement st2 = req.co.prepareStatement("SELECT MAX(obj_id) AS obj_id FROM t_map_object");
            ResultSet rs = st2.executeQuery();
            res.dataSetToArray(rs);
            rs.close();
            st2.close();
            res.setMessage("This element has been successfully inserted into the database.");
            return res;
        }  catch (Exception e) {
            logger.error(e.getMessage());
            Response res = new Response("error");
            res.setMessage("Error when inserting the object in the database.");
            return res;
        }
    }
}
