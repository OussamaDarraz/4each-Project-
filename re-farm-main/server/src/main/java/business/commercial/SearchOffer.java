package business.commercial;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchOffer extends TemplateBusiness {

    public static Response seekShow(Request req) {
            try {

                Response res = new Response("success");
                ResultSet rs;
                PreparedStatement st;
                String query = "Select reference , name_product,poids,categorie ,cust_type,coefficient,date_creation,date_ligne ,date_expiration, prix  from t_off_offer too  inner join t_off_product_type topt on too.id_offer =topt.id_prod inner join t_off_customer_type toct on too.id_offer =toct.id_customer where reference like ?";
                st = req.co.prepareStatement(query);
                st.setString(1, req.getParam("reference"));
                rs = st.executeQuery();
                res.dataSetToArray(rs);

                rs.close();
                st.close();

                return res;
            } catch (SQLException e) {
                e.getMessage();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Response("error","Recherche impossible car paramètre inexistant");
        }

    }
