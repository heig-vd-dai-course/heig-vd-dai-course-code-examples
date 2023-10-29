import java.io.*;
import java.net.*;

class TcpServerSimpleBinaryExample {

  private static final int PORT = 1234;
  private static final int SERVER_ID = (int) (Math.random() * 1000000);
  private static final byte[] BINARY_DATA = { 0x48, 0x65, 0x6C, 0x6C, 0x6F };

  public static void main(String args[]) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println(
        "[Server " + SERVER_ID + "] starting with id " + SERVER_ID
      );
      System.out.println(
        "[Server " + SERVER_ID + "] listening on port " + PORT
      );

      while (true) {
        try (
          Socket socket = serverSocket.accept();
          BufferedInputStream in = new BufferedInputStream(
            socket.getInputStream()
          );
          BufferedOutputStream out = new BufferedOutputStream(
            socket.getOutputStream()
          );
        ) {
          System.out.println(
            "[Server " + SERVER_ID + "] new client connection"
          );

          // store the data received from the client
          ByteArrayOutputStream byteArrayOutputStream =
            new ByteArrayOutputStream();

          int i;
          while ((i = in.read()) != -1) {
            byteArrayOutputStream.write(i);
          }

          byte[] receivedData = byteArrayOutputStream.toByteArray();

          System.out.println(
            "[Server " + SERVER_ID + "] received binary data from client: " + i
          );
          System.out.println(
            "[Server " +
            SERVER_ID +
            "] sending response to client: " +
            BINARY_DATA
          );
        } catch (IOException e) {
          System.out.println(
            "[Server " + SERVER_ID + "] socket exception: " + e
          );
        }
      }
    } catch (IOException e) {
      System.out.println("[Server " + SERVER_ID + "] socket exception: " + e);
    }
  }
}
