package business.commercial;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizeOffer extends TemplateBusiness {

        public static Response updateSpec1(Request req){

            try {
                PreparedStatement st6;
                st6 = req.co.prepareStatement("Update t_off_customer_type set cust_type=?,coefficient=? where id_customer=(select id_customer from t_off_offer where reference=?)");

                st6.setString(1, req.getParam("cust_type"));
                st6.setInt(2, Integer.parseInt(req.getParam("coefficient")));
                st6.setString(3,req.getParam("reference"));
                st6.executeUpdate();
                Response res = new Response("Mise à jour réussie,Bravo");
                st6.close();
                return res;
            } catch (SQLException e) {
                e.getMessage();
            }

            Response res = new Response("error");
            res.setMessage("Update || Insertion échouée");
            return res;
        }

    public static Response updateSpec2(Request req){

        try {
            PreparedStatement st5;
            st5 = req.co.prepareStatement("UPDATE t_off_offer SET date_ligne=?, date_expiration=?, prix=?  WHERE reference = ?");

            st5.setString(1, req.getParam("date_ligne"));
            st5.setString(2, req.getParam("date_expiration"));
            st5.setInt(3,  Integer.parseInt(req.getParam("prix")));
            st5.setString(4,req.getParam("reference"));
            st5.executeUpdate();

            Response res = new Response("Mise à jour réussie,Bravo");
            st5.close();

            return res;
        } catch (SQLException e) {
            e.getMessage();
        }

        Response res = new Response("error");
        res.setMessage("Update || Insertion échouée");
        return res;
    }
        }

