package mocks.Sockets.Server;

import dasBoot.Sockets.ServerSocket.iServerSocket;
import dasBoot.Sockets.Socket.iSocket;

import mocks.Sockets.Client._UmweltSocket;

public class _UmweltServerSocket implements iServerSocket {
  private boolean listen = false;
  public boolean open;
  iSocket listener;
  private int port;

  public _UmweltServerSocket(int port) {
    open = true;
    listener = new _UmweltSocket();
    this.port = port;
  }

  public void mockOpen() {
    open = true;
  }

  public void stubListener(iSocket listener) {
    this.listener = listener;
  }

  public iSocket listen() {
    listen = true;
    open = false;
    return listener;
  }

  public void close() {  }
  public boolean isOpen() { return open; }
  public boolean listening() { return listen; }
  public int getPort() { return port; }
}
