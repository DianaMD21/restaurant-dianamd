package com.diana.restaurant.controllers.stock;

import com.diana.restaurant.controllers.StockController;
import com.diana.restaurant.services.implementation.StockServiceImpl;
import com.diana.restaurant.services.interfaces.StockService;
import com.diana.restaurant.util.fixtures.services.StockServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StockControllerIntegrationTest {
  private StockController stockController;
  private StockService stockServiceSpy;

  @BeforeEach
  public void setUp() {
    StockService stockService = new StockServiceImpl();
    stockServiceSpy = Mockito.spy(stockService);
    stockController = new StockController(stockServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundStock() {
    var stock = StockServiceFixtures.buildStock();
    stockController.add(stock);
    var stockFound = stockController.findById(stock.getId());
    Assertions.assertEquals(stockFound, stock);
    Mockito.verify(stockServiceSpy, Mockito.times(1)).findById(Mockito.eq(stock.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllStocks() {
    var stocks = StockServiceFixtures.buildStocks(3);
    stocks.stream().forEach(stockController::add);
    var stocksFound = stockController.getAll();
    Assertions.assertEquals(stocks, stocksFound);
  }

  @Test
  public void add_ShouldAddStock() {
    var stock = StockServiceFixtures.buildStock();
    var stockAdded = stockController.add(stock);
    Assertions.assertEquals(stockAdded, stock);
    Mockito.verify(stockServiceSpy, Mockito.times(1)).add(Mockito.eq(stock));
  }

  @Test
  public void delete_ShouldDeleteStock() {
    var stocks = StockServiceFixtures.buildStocks(3);
    stocks.stream().forEach(stockController::add);
    var deletedStock = stockController.deleteById(stocks.get(0).getId());
    var deletedStockToVerify = StockServiceFixtures.buildStock(stocks.get(0));
    Assertions.assertEquals(deletedStock, stocks.get(0));
    Mockito.verify(stockServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedStockToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateStock() {
    var stockToUpdate = StockServiceFixtures.buildStock();
    stockController.add(stockToUpdate);
    stockToUpdate.setName("Testing Stock fake");
    var stockToTestVerify = StockServiceFixtures.buildStock(stockToUpdate);
    var updatedStock = stockController.update(stockToUpdate);
    Assertions.assertEquals(updatedStock, stockToUpdate);
    Mockito.verify(stockServiceSpy, Mockito.times(1)).update(Mockito.eq(stockToTestVerify));
  }
}
