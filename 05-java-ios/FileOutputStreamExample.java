import java.io.*;

class FileOutputStreamExample {

  public static void main(String[] args) throws IOException {
    FileOutputStream fos = new FileOutputStream("FileOutputStreamExample.bin");

    for (int i = 0; i < 255; i++) {
      fos.write(i);
    }

    fos.close();
  }
}
