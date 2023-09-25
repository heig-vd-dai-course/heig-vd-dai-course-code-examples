import java.io.*;

class FileInputStreamExample {

  public static void main(String[] args) throws IOException {
    FileInputStream fis = new FileInputStream("FileInputStreamExample.java");

    int b; // is -1 if end of file or byte value 0-255
    while ((b = fis.read()) != -1) {
      System.out.print((char) b);
    }

    fis.close();
  }
}
