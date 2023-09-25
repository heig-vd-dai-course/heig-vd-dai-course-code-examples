import java.io.*;
import java.nio.charset.StandardCharsets;

class StreamReaderWriterExample {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(
      new InputStreamReader(
        new FileInputStream("StreamReaderWriterExample.java"),
        StandardCharsets.UTF_8
      )
    );

    BufferedWriter writer = new BufferedWriter(
      new OutputStreamWriter(
        new FileOutputStream("StreamReaderWriterExample.txt"),
        StandardCharsets.UTF_8
      )
    );

    int c; // is -1 if end of file or char value 0-65535
    while ((c = reader.read()) != -1) {
      writer.write(c);
    }

    writer.flush();
    writer.close();
    reader.close();
  }
}
