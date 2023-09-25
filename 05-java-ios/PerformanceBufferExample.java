import java.io.*;

class PerformanceBufferExample {

  public static void main(String[] args) throws IOException {
    long start = System.nanoTime();

    // Write 10 MB of data to a file
    BufferedOutputStream bos = new BufferedOutputStream(
      new FileOutputStream("PerformanceBufferExample.bin")
    );
    for (int i = 0; i < 10 * 1024 * 1024; i++) {
      bos.write(1);
    }

    bos.flush();
    bos.close();

    long end = System.nanoTime();

    System.out.println("Write time: " + (end - start) / 1000000 + " ms");

    start = System.nanoTime();

    // Read 10 MB of data from a file
    BufferedInputStream bis = new BufferedInputStream(
      new FileInputStream("PerformanceBufferExample.bin")
    );

    int b;
    while ((b = bis.read()) != -1) {
      // Do nothing
    }

    bis.close();

    end = System.nanoTime();

    System.out.println("Read time:  " + (end - start) / 1000000 + " ms");
  }
}
