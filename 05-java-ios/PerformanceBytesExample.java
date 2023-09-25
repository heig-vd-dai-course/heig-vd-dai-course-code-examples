import java.io.*;

class PerformanceBytesExample {

  public static void main(String[] args) throws IOException {
    long start = System.nanoTime();

    // Write 10 MB of data to a file
    FileOutputStream fos = new FileOutputStream("PerformanceBytesExample.bin");
    for (int i = 0; i < 10 * 1024 * 1024; i++) {
      fos.write(1);
    }

    fos.close();

    long end = System.nanoTime();

    System.out.println("Write time: " + (end - start) / 1000000 + " ms");

    start = System.nanoTime();

    // Read 10 MB of data from a file
    FileInputStream fis = new FileInputStream("PerformanceBytesExample.bin");
    int b;
    while ((b = fis.read()) != -1) {
      // Do nothing
    }

    fis.close();

    end = System.nanoTime();

    System.out.println("Read time:  " + (end - start) / 1000000 + " ms");
  }
}
