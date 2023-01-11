package util.fixtures;

import entities.Stock;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class StockServiceFixtures {

  public static List<Stock> buildStocks(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(StockServiceFixtures::buildStock)
        .collect(Collectors.toList());
  }

  public static Stock buildStock() {
    return buildStock((Stock) null);
  }

  public static Stock buildStock(Long id) {
    var stockExample = new Stock();
    stockExample.setId(id);
    return buildStock(stockExample);
  }

  public static Stock buildStock(Stock stockExample) {
    var stock = new Stock();
    StockFixtures.buildStock(stock, stockExample);
    return stock;
  }
}
