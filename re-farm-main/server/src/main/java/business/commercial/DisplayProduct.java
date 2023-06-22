package business.commercial;

import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayProduct {
    public  static Response playprod(Request req){

        try {

            Response res = new Response("success");
            ResultSet rs;
            PreparedStatement st;
            st = req.co.prepareStatement("Select name_product, poids from t_off_product_type");
            rs = st.executeQuery();
           res.dataSetToArray(rs);
            rs.close();
            st.close();
           return res;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response("Affichage impossible");
    }


}
