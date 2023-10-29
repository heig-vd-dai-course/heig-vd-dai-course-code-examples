import java.io.*;
import java.net.*;

class TcpClientBinaryExample {

  private static final String HOST = "localhost";
  private static final int PORT = 1234;
  private static final int CLIENT_ID = (int) (Math.random() * 1000000);
  private static final byte[] BINARY_DATA = { 0x48, 0x65, 0x6C, 0x6C, 0x6F };

  public static void main(String args[]) {
    System.out.println(
      "[Client " + CLIENT_ID + "] starting with id " + CLIENT_ID
    );
    System.out.println(
      "[Client " + CLIENT_ID + "] connecting to " + HOST + ":" + PORT
    );

    try (
      Socket socket = new Socket(HOST, PORT);
      BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
      BufferedOutputStream out = new BufferedOutputStream(
        socket.getOutputStream()
      );
    ) {
      System.out.println(
        "[Client " + CLIENT_ID + "] connected to " + HOST + ":" + PORT
      );
      System.out.println(
        "[Client " +
        CLIENT_ID +
        "] sending binary data to server " +
        HOST +
        ":" +
        PORT +
        ": " +
        BINARY_DATA
      );

      out.write(BINARY_DATA);
      out.flush();

      System.out.println(
        "[Client " + CLIENT_ID + "] response from server: " + in.read()
      );
    } catch (IOException e) {
      System.out.println("[Client " + CLIENT_ID + "] socket exception: " + e);
    }
  }
}
