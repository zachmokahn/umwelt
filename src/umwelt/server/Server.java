package umwelt.server;

import java.io.IOException;

import umwelt.server.Handlers.RequestHandler;
import umwelt.server.Routers.UmweltRouter;
import umwelt.server.Routers.iRouter;
import umwelt.server.Sockets.iServerSocket;

public class Server {
  private int port;
  private iServerSocket serverSocket;
  private iRouter router;

  public Server(iServerSocket serverSocket) {
    this.router = new UmweltRouter();
    this.serverSocket = serverSocket;
    setPort();
  }

  public int getPort() {
    return port;
  }

  public void start() throws IOException {
    RequestHandler handler = new RequestHandler(serverSocket, router);
    handler.start();
  }

  private void setPort() {
    this.port = serverSocket.getPort();
  }
}
