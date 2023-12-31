import java.io.*;
import java.nio.charset.StandardCharsets;

class ExceptionHandlingExample {

  public static void main(String[] args) {
    badExceptionHandlingExample();
    tryWithResources();
  }

  public static void badExceptionHandlingExample() {
    Reader reader = null;
    Writer writer = null;

    try {
      reader = new FileReader("missing.file");
      writer = new FileWriter("missing.file");
      writer.write(reader.read());
    } catch (IOException e) {
      System.out.println("Exception: " + e);
    } finally {
      try {
        writer.close();
      } catch (Exception e) {
        System.out.println("Exception in close writer: " + e);
      }
      try {
        reader.close();
      } catch (Exception e) {
        System.out.println("Exception in close reader: " + e);
      }
    }
  }

  public static void tryWithResources() {
    try (
      Reader reader = new FileReader("missing.file");
      Writer writer = new FileWriter("missing.file")
    ) {
      writer.write(reader.read());
    } catch (IOException e) {
      System.out.println("Exception: " + e);
    }
  }
}
