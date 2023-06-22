package business.commercial;

import business.TemplateBusiness;
import server.Request;
import server.Response;

import java.sql.PreparedStatement;

public class RemoveOffer extends TemplateBusiness {

    public static Response supShow1(Request req) {

        try {
            PreparedStatement sd1;
            sd1 = req.co.prepareStatement(" Delete  from t_off_offer where id_offer=(select id_offer from t_off_offer where reference=?)");
           sd1.setString(1, req.getParam("reference"));
            sd1.executeUpdate();

            Response res1 = new Response("success");
            sd1.close();
            return res1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response("error");}

        public static Response supShow2 (Request req){

            try {
                PreparedStatement sd2;

                sd2 = req.co.prepareStatement("Delete  from t_off_customer_type where id_customer=(select id_customer from t_off_customer_type where cust_type=?)");
                sd2.setString(1, req.getParam("cust_type"));
                sd2.executeUpdate();

                Response res2 = new Response("success");

                sd2.close();

                return res2;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Response("error");}

            public static Response supShow3 (Request req){

                try {
                    PreparedStatement sd3;
                    sd3 = req.co.prepareStatement("Delete from t_off_product_type where id_prod=(select id_prod from t_off_product_type where name_product=?)");
                    sd3.setString(1, req.getParam("name_product"));
                    sd3.executeUpdate();
                    Response res = new Response("success");

                    sd3.close();
                    return res;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new Response("error");

            }
        }


