import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServerSingleThreadTextualExample {

  private static final int PORT = 1234;
  private static final int SERVER_ID = (int) (Math.random() * 1000000);
  private static final String TEXTUAL_DATA = "👋 from Server " + SERVER_ID;

  public static void main(String[] args) {
    ExecutorService executor = null;

    try (
      ServerSocket serverSocket = new ServerSocket(PORT);
      // This can only be done in Java 19+. Update for next year.
      // ExecutorService executor = Executors.newSingleThreadExecutor();
    ) {
      executor = Executors.newSingleThreadExecutor();

      System.out.println(
        "[Server " + SERVER_ID + "] starting with id " + SERVER_ID
      );
      System.out.println(
        "[Server " + SERVER_ID + "] listening on port " + PORT
      );

      while (true) {
        Socket clientSocket = serverSocket.accept();
        executor.submit(new ClientHandler(clientSocket));
      }
    } catch (IOException e) {
      System.out.println("[Server " + SERVER_ID + "] exception: " + e);
    } finally {
      if (executor != null) {
        executor.shutdown();
      }
    }
  }

  static class ClientHandler implements Runnable {

    private final Socket socket;

    public ClientHandler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try (
        socket; // This allow to use try-with-resources with the socket
        BufferedReader in = new BufferedReader(
          new InputStreamReader(
            socket.getInputStream(),
            StandardCharsets.US_ASCII
          )
        );
        BufferedWriter out = new BufferedWriter(
          new OutputStreamWriter(
            socket.getOutputStream(),
            StandardCharsets.US_ASCII
          )
        )
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
          "[Server " +
          SERVER_ID +
          "] received textual data from client: " +
          in.readLine()
        );

        try {
          System.out.println(
            "[Server " +
            SERVER_ID +
            "] sleeping for 10 seconds to simulate a long operation"
          );
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }

        System.out.println(
          "[Server " +
          SERVER_ID +
          "] sending response to client: " +
          TEXTUAL_DATA
        );

        out.write(TEXTUAL_DATA + "\n");
        out.flush();

        System.out.println("[Server " + SERVER_ID + "] closing connection");
      } catch (IOException e) {
        System.out.println("[Server " + SERVER_ID + "] exception: " + e);
      }
    }
  }
}
