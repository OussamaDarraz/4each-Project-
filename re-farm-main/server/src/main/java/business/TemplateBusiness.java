package business;

import server.Request;
import server.Response;

public abstract class TemplateBusiness {
     static Response run(Request req) { return new Response("error"); }
     // TODO add a message in the Response to get more information about the error.
}
