import java.io.*;
import java.nio.charset.StandardCharsets;

class TextEndOfLineCharactersExample {

  public static String END_OF_LINE = "\n";

  public static void main(String[] args) throws IOException {
    InputStream is = new FileInputStream("LineEndingsExample.java");
    Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
    BufferedReader br = new BufferedReader(reader);

    OutputStream os = new FileOutputStream("LineEndingsExample.txt");
    Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8);
    BufferedWriter bw = new BufferedWriter(writer);

    String line;
    while ((line = br.readLine()) != null) {
      // Careful: line does not contain end-of-line characters
      bw.write(line + END_OF_LINE);
    }

    bw.flush();
    br.close();
    is.close();
  }
}
