package umwelt.mocks.Sockets.Socket;
import umwelt.server.Sockets.Socket.iSocket;
import umwelt.server.Utils.iParser;

import java.io.IOException;

import umwelt.mocks.Requests._Request;
import umwelt.server.Responses.iResponse;

public class _Volksempfanger implements iSocket {
  public boolean wasRequested = false;
  public boolean wasResponded = false;
  public boolean wasClosed = false;
  public iResponse response;
  public String requestData;
  public _Request request;

  public _Volksempfanger() {
    request = new _Request("test", "/test");
  }

  public _Request request(iParser parser) throws IOException {
    wasRequested = true;
    return request;
  }

  public void stubRequest(String method, String uri) {
    request = new _Request(method, uri);
  }

  public void respondWith(iResponse response) throws Exception {
    wasResponded = true;
    this.response = response;
  }

  public void close() {
    wasClosed = true;
  }
}
