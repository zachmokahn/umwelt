/* src.umwelt.test.Handlers.RouteHandlerTest */
package umwelt.server.Handlers;

import umwelt.server.Communication.Requests.iRequest;
import umwelt.server.Communication.Responses.iResponse;
import umwelt.server.Routers.iRouter;

public class RouteHandler {
  private iRouter router;

  public RouteHandler(iRouter router) {
    setRouter(router);
  }

  public iResponse delegate(iRequest request) {
    return router.route(request);
  }

  public void setRouter(iRouter router) {
    this.router = router;
  }
}
