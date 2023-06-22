# Documentation Developpers re-farm 


## Server 

### New Class in Business
For each new functionality you have to add a class named explicitly which extends the abstract class TemplateBusiness and which contains a run() method.

```
public class AddNewSensor extends TemplateBusiness {
    public Response run(Request req) {
        // code
        return new Response("success");
    }
}
```

#### Examples for INSERT, SELECT, UPDATE and DELETE

##### SELECT 
```
 public static Response run(Request req) {
    try {
        Response res = new Response("succes");
        ResultSet rs;
        PreparedStatement st;
        if (req.getParam("matricule") != null) {
            st = req.co.prepareStatement("SELECT * FROM t_test_r2 WHERE matricule = ?");
            st.setInt(1, Integer.parseInt(req.getParam("matricule")));
        } else {
            st = req.co.prepareStatement("SELECT matricule, lastname, firstname, age FROM t_test_r2");
        }
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
    return new Response("error");
}
```

##### INSERT 
```
 public static Response run(Request req) {
    try {
        PreparedStatement st = req.co.prepareStatement("INSERT INTO t_test_r2(matricule, lastname, firstname, age) VALUES (?, ?, ?, ?)");
        st.setInt(1, Integer.parseInt(req.getParam("matricule")));
        st.setString(2, req.getParam("lastname"));
        st.setString(3, req.getParam("firstname"));
        st.setInt(4, Integer.parseInt(req.getParam("age")));
        st.executeUpdate();
        Response res = new Response("succes");
        st.close();
        return res;
    } catch (SQLException sqle) {
        sqle.getMessage();
    }
    return new Response("error");
}
```

##### UPDATE 
```
 public static Response run(Request req) {
    try {
        if (req.getParam("matricule") != null) {
            PreparedStatement st = req.co.prepareStatement("UPDATE t_test_r2 SET lastname = ?, firstname = ?, age = ? WHERE matricule = ?");
            st.setString(1, req.getParam("lastname"));
            st.setString(2, req.getParam("firstname"));
            st.setInt(3, Integer.parseInt(req.getParam("age")));
            st.setInt(4, Integer.parseInt(req.getParam("matricule")));
            st.executeUpdate();
            Response res = new Response("succes");
            st.close();
            return res;
        }
    } catch (SQLException sqle) {
        sqle.getMessage();
    }
    return new Response("error");
}
```

##### DELETE 
```
public static Response run(Request req) {
    try {
        PreparedStatement st;
        if (req.getParam("matricule") != null) {
            st = req.co.prepareStatement("DELETE FROM t_test_r2 WHERE matricule = ?");
            st.setInt(1, Integer.parseInt(req.getParam("matricule")));
        } else {
            st = req.co.prepareStatement("DELETE FROM t_test_r2");
        }
        st.executeUpdate();
        Response res = new Response("succes");
        st.close();
        return res;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return new Response("error");
}
```

## Request and Response (network side)

### Request
The Request class allows to convert the JSON sent by the service into an easy to use object.

### Response
The Response class is used to format the data provided by the network so that it can be converted into JSON.

# Client

## Request and Response (service side)

### Request
The Request class (service side) allows to generate the request to send to the network with a process to know which method must be called in the network and optional parameters if needed.

### Response
The Response class (service side) allows to retrieve the JSON from the network and transform it into an easy to use object.
