package umwelt.mocks.Routers;

import umwelt.mocks.Responses._Response;
import umwelt.server.Controllers.iController;
import umwelt.server.Requests.iRequest;
import umwelt.server.Routers.iRouter;

public class _Router implements iRouter {
  public boolean wasRouted = false;
  public iRequest routed;
  public _Router(iController[] controllers) {  }

  @Override
  public _Response route(iRequest request) {
    wasRouted = true;
    routed = request;
    return new _Response();
  }

  public boolean routed_with(iRequest request) {
    return routed.equals(request);
  }
}
