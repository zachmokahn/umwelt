package umwelt.server.Controllers;

import java.util.Hashtable;
import umwelt.server.Communication.Requests.iRequest;
import umwelt.server.Communication.Responses.iResponse;

public interface iController {
  public boolean valid(iRequest request);
  public iResponse handle(iRequest request);
  public Hashtable<String, iResponse> getRoutes();
}