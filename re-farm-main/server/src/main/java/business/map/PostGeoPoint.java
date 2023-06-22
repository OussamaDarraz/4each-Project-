package business.map;

import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostGeoPoint {
    public static Response run(Request req) {
        try {
            int x = Integer.parseInt(req.getParam("x"));
            int y = Integer.parseInt(req.getParam("y"));
            if (x < 0 || 100 < x || y < 0 || 100 < y) {
                return new Response("error", "Your point : X="+ x +" and Y="+ y +" should be between 0 and 100.");
            }
            PreparedStatement st = req.co.prepareStatement("call add_geo_point(?, ?, ?)");
            st.setInt(1, Integer.parseInt(req.getParam("id")));
            st.setInt(2, x);
            st.setInt(3, y);
            st.executeUpdate();
            Response res = new Response("succes");
            res.setMessage("Position added successfully.");
            st.close();
            return res;
        } catch (SQLException sqle) {
            Response res = new Response("error");
            res.setMessage("Error when inserting the position in the database.");
            return res;
        }

    }
}
