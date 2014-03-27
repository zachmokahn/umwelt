package dasBoot.test.Sockets.Socket;

import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Test;

import dasBoot.mocks.Responses._Response;
import dasBoot.mocks.Sockets.Socket._Socket;
import dasBoot.mocks.Util._Parser;
import dasBoot.server.Sockets.Socket.Volksempfanger;

public class VolksempfangerTest {
  @Test public void ReadsInputStreamFromSocket() throws IOException {
    _Socket socket = new _Socket();
    Volksempfanger volksempfanger = new Volksempfanger(socket);
    assertNull(volksempfanger.request(new _Parser()));
    assertTrue(socket.gotInputStream);
  }

  @Test public void writesOutputToSocket() throws Exception {
    _Socket socket = new _Socket();
    Volksempfanger volksempfanger = new Volksempfanger(socket);
    assertFalse(socket.gotOutputStream);
    volksempfanger.respondWith(new _Response());
    assertTrue(socket.gotOutputStream);
  }
}