import java.io.*;
import java.net.*;

class TcpServerSimpleBinaryExample {

  private static final int PORT = 1234;
  private static final int SERVER_ID = (int) (Math.random() * 1000000);
  private static final String FILENAME = "server.bin";

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
          BufferedOutputStream bos = new BufferedOutputStream(
            new FileOutputStream(FILENAME)
          );
          BufferedInputStream in = new BufferedInputStream(
            socket.getInputStream()
          );
          BufferedOutputStream out = new BufferedOutputStream(
            socket.getOutputStream()
          );
        ) {
          System.out.println(
            "[Server " +
            SERVER_ID +
            "] new client connected from " +
            socket.getInetAddress().getHostAddress() +
            ":" +
            socket.getPort()
          );

          System.out.println(
            "[Server " + SERVER_ID + "] receiving binary data from client..."
          );

          int i;
          while ((i = in.read()) != -1) {
            bos.write(i);
          }

          System.out.println(
            "[Server " + SERVER_ID + "] binary data saved in '" + FILENAME + "'"
          );

          System.out.println("[Server " + SERVER_ID + "] closing connection");
        } catch (IOException e) {
          System.out.println("[Server " + SERVER_ID + "] exception: " + e);
        }
      }
    } catch (IOException e) {
      System.out.println("[Server " + SERVER_ID + "] exception: " + e);
    }
  }
}
