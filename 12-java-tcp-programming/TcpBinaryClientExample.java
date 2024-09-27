import java.io.*;
import java.net.*;

class TcpBinaryClientExample {

  private static final String HOST = "localhost";
  private static final int PORT = 1234;
  private static final int CLIENT_ID = (int) (Math.random() * 1000000);
  private static final int SIZE_IN_BYTES = 1024 * 1024; // 1 MB
  private static final String FILENAME = "client.bin";

  public static void main(String args[]) {
    System.out.println(
      "[Client " + CLIENT_ID + "] starting with id " + CLIENT_ID
    );

    System.out.println(
      "[Client " +
      CLIENT_ID +
      "] generating file " +
      FILENAME +
      " of size " +
      SIZE_IN_BYTES +
      " bytes"
    );

    try (
      BufferedOutputStream bos = new BufferedOutputStream(
        new FileOutputStream(FILENAME)
      );
    ) {
      for (int i = 0; i < SIZE_IN_BYTES; i++) {
        bos.write(i);
      }
    } catch (IOException e) {
      System.out.println("[Client " + CLIENT_ID + "] io exception: " + e);
    }

    System.out.println(
      "[Client " + CLIENT_ID + "] connecting to " + HOST + ":" + PORT
    );

    try (
      Socket socket = new Socket(HOST, PORT);
      BufferedInputStream bis = new BufferedInputStream(
        new FileInputStream(FILENAME)
      );
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
        "] sending " +
        FILENAME +
        " to server " +
        HOST +
        ":" +
        PORT
      );

      int b;
      while ((b = bis.read()) != -1) {
        out.write(b);
        out.flush();
      }

      System.out.println("[Client " + CLIENT_ID + "] closing connection");
    } catch (IOException e) {
      System.out.println("[Client " + CLIENT_ID + "] exception: " + e);
    }
  }
}
