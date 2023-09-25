import java.io.*;
import java.nio.charset.StandardCharsets;

class LineEndingsExample {

  public static void main(String[] args) throws IOException {
    BufferedReader is = new BufferedReader(
      new InputStreamReader(
        new FileInputStream("LineEndingsExample.java"),
        StandardCharsets.UTF_8
      )
    );

    BufferedWriter os = new BufferedWriter(
      new OutputStreamWriter(
        new FileOutputStream("LineEndingsExample.txt"),
        StandardCharsets.UTF_8
      )
    );

    String line;

    while ((line = is.readLine()) != null) {
      // Careful: line does not contain end-of-line characters
      os.write(line + "\n");
    }

    os.flush();
    os.close();
    is.close();
  }
}
