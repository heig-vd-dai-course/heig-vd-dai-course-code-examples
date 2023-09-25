class StringsExample {

  public static void main(String[] args) {
    daiString();
    daiCodePoints();
    abgoosht();
  }

  public static void daiString() {
    String text = "I 🔥 DAI";

    char[] characters = text.toCharArray();

    System.out.println(
      "The string " + text + " has " + characters.length + " characters:"
    );

    for (char character : characters) {
      System.out.println(character);
    }
  }

  public static void daiCodePoints() {
    String text = "I 🔥 DAI";

    int[] codepoints = text.codePoints().toArray();

    System.out.println(
      "The string " + text + " has " + codepoints.length + " codepoints:"
    );

    for (int codepoint : codepoints) {
      System.out.println(Character.toChars(codepoint));
    }
  }

  public static void abgoosht() {
    // This means "broth" in Persian
    String text = "آبگوشت";

    char[] characters = text.toCharArray();

    System.out.println(
      "The string " + text + " has " + characters.length + " characters:"
    );

    for (char character : characters) {
      System.out.println(character);
    }
  }
}
