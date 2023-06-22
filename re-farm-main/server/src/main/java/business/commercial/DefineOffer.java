package business.commercial;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.*;

public class DefineOffer  extends TemplateBusiness {

       public static Response insertCreate1(Request req){

            try {

                PreparedStatement st1 = req.co.prepareStatement("INSERT INTO t_off_product_type(name_product,poids ) VALUES (?, ?)"
                                                                    , Statement.RETURN_GENERATED_KEYS);

                st1.setString(1, req.getParam("name_product"));
                st1.setInt(2, Integer.parseInt(req.getParam("poids")));

                st1.executeUpdate();

                // Retrieve inserted id
                ResultSet rs = st1.getGeneratedKeys();
                int last_inserted_id = 0;
                if(rs.next()) last_inserted_id = rs.getInt(1);

                Response res = new Response("success");
                res.setMessage(String.valueOf(last_inserted_id));
                st1.close();

                return res;

            } catch (SQLException sqle1) {
                sqle1.getMessage();
            }
           Response res = new Response("error");
           res.setMessage("Insertion échouée, Désolée");
           return res;
        }
    public static Response insertCreate2 (Request req){

        try {

            System.out.println(req.getParam("id_prod"));
            PreparedStatement st1 = req.co.prepareStatement("INSERT INTO t_off_offer(reference, categorie, date_creation,id_prod ) VALUES (?, ?, ?, ?)");

            st1.setString(1, req.getParam("reference"));
            st1.setString(2, req.getParam("categorie"));
            st1.setDate(3, Date.valueOf (req.getParam("date_creation")));
            st1.setInt(4, Integer.parseInt(req.getParam("id_prod")));

            st1.executeUpdate();

            Response res = new Response("success");
            st1.close();

            return res;
        } catch (SQLException sqle1) {
            sqle1.getMessage();
        }
        Response res = new Response("error");
        res.setMessage("Insertion échouée, Désolée");
        return res;
    }



    public static Response insertSpec1 (Request req){

            try {
                PreparedStatement st4 = req.co.prepareStatement("INSERT INTO t_off_customer_type(cust_type,coefficient) VALUES (?, ?)"
                    , Statement.RETURN_GENERATED_KEYS);


                st4.setString(1, req.getParam("cust_type"));
                st4.setInt(2,Integer.parseInt(req.getParam("coefficient")));
                st4.executeUpdate();

                // Retrieve inserted id
                ResultSet rs = st4.getGeneratedKeys();
                int last_inserted_id = 0;
                if(rs.next()) last_inserted_id = rs.getInt(1);

                Response res = new Response("success");
                res.setMessage(String.valueOf(last_inserted_id));

                st4.close();
                return res;
            } catch (SQLException sqle2) {
                sqle2.getMessage();
            }
            Response res = new Response("error");
            res.setMessage("Insertion échouée, Désolée");
            return res;
        }

    public static Response insertSpec2 (Request req){


        try {
            PreparedStatement st3 = req.co.prepareStatement("UPDATE t_off_offer SET date_ligne=?, date_expiration=?, prix=?, id_customer=? WHERE reference = ?");

            st3.setDate(1, Date.valueOf(req.getParam("date_ligne")));
            st3.setDate(2, Date.valueOf(req.getParam("date_expiration")));
            st3.setInt(3, Integer.parseInt(req.getParam("prix")));
            st3.setInt(4, Integer.parseInt(req.getParam("id_customer")));
            st3.setString(5, req.getParam("reference"));

            st3.executeUpdate();

            Response res = new Response("success");
            st3.close();

            return res;
        } catch (SQLException sqle2) {
            sqle2.getMessage();
        }
        Response res = new Response("error");
        res.setMessage("Insertion échouée, Désolée");
        return res;
    }



}



