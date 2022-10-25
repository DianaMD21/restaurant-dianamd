package util;

public final class ClientServiceUtility {
  public static String createCode(Long code) {
    return String.format(String.format("C_%s", code));
  }
}
