import java.io.*;

/**
 * This program shows how to handle exceptions in Java. It shows how to handle exceptions in various
 * situations, how to close resources properly, and how to use the try-with-resources statement.
 */
class DealingWithErrorsExample {

  public static void main(String[] args) {
    // Bad practice
    tryCatchWithoutFinallyExample();

    // Better practice
    tryCatchFinallyExample();

    // Best practice
    tryWithResourcesExample();
  }

  /**
   * This example is a bad practice. It catches an exception but does not close the resources
   * properly. It is a bad practice because it can lead to resource leaks (= resources that are not
   * closed properly and that are not available for other parts of the program).
   */
  public static void tryCatchWithoutFinallyExample() {
    try {
      Reader reader = new FileReader("missing.file");
      Writer writer = new FileWriter("missing.file");

      writer.write(reader.read());
    } catch (IOException e) {
      System.out.println("Exception: " + e);
    }
  }

  /**
   * This example is a better practice. It catches an exception and closes the resources properly.
   * It is a better practice because it avoids resource leaks as it always closes the resources
   * properly with the `finally` block.
   */
  public static void tryCatchFinallyExample() {
    Reader reader = null;
    Writer writer = null;

    try {
      reader = new FileReader("missing.file");
      writer = new FileWriter("missing.file");

      writer.write(reader.read());
    } catch (IOException e) {
      System.out.println("Exception: " + e);
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          System.out.println("Exception in close writer: " + e);
        }
      }

      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          System.out.println("Exception in close reader: " + e);
        }
      }
    }
  }

  /**
   * This example is the best practice. It catches an exception and closes the resources properly
   * with the try-with-resources statement. It is the best practice because it avoids resource leaks
   * as it always closes the resources properly with the try-with-resources statement. It is also
   * more concise and easier to read than the previous examples.
   */
  public static void tryWithResourcesExample() {
    try (Reader reader = new FileReader("missing.file");
        Writer writer = new FileWriter("missing.file")) {
      writer.write(reader.read());
    } catch (IOException e) {
      System.out.println("Exception: " + e);
    }
  }
}
