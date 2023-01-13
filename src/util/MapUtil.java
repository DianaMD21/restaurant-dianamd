package util;

import entities.Cashier;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class MapUtil {

  public static <K, V> Map<K, V> of(AbstractMap.SimpleEntry<K, V>... entries) {
    var resultMap = new HashMap<K, V>();

    Stream.of(entries).forEach(entry -> resultMap.put(entry.getKey(), entry.getValue()));

    return resultMap;
  }
}
