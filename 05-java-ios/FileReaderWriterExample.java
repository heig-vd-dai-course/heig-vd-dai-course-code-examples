import java.io.*;

class FileReaderWriterExample {

  public static void main(String[] args) throws IOException {
    FileReader reader = new FileReader("FileReaderWriterExample.java");
    FileWriter writer = new FileWriter("FileReaderWriterExample.txt");

    int c; // is -1 if end of file or char value 0-65535
    while ((c = reader.read()) != -1) {
      writer.write(c);
    }

    writer.flush();
    writer.close();
    reader.close();
  }
}
