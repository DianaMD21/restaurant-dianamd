package com.diana.restaurant.controllers.stock;

import com.diana.restaurant.controllers.StockController;
import com.diana.restaurant.services.interfaces.StockService;
import com.diana.restaurant.util.fixtures.services.StockServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StockControllerTest {
  private StockController stockController;
  private StockService stockServiceMock;

  @BeforeEach
  public void setup() {
    stockServiceMock = Mockito.mock(StockService.class);
    stockController = new StockController(stockServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfStocks() {
    var stocks = StockServiceFixtures.buildStocks(3);
    Mockito.when(stockServiceMock.getAll()).thenReturn(stocks);
    var stocksReturned = stockController.getAll();
    Assertions.assertEquals(stocks, stocksReturned);
    Mockito.verify(stockServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddStock() {
    var stock = StockServiceFixtures.buildStock();
    var expectedStock = StockServiceFixtures.buildStock(1L);
    Mockito.when(stockServiceMock.add(Mockito.eq(stock))).thenReturn(expectedStock);
    var stockAdded = stockController.add(stock);
    Assertions.assertEquals(expectedStock, stockAdded);
    Mockito.verify(stockServiceMock, Mockito.times(1)).add(Mockito.eq(stock));
  }

  @Test()
  public void delete_ShouldDeleteStock() {
    var stock = StockServiceFixtures.buildStock(1L);
    Mockito.when(stockServiceMock.delete(stock.getId())).thenReturn(stock);
    var stockDeleted = stockController.deleteById(stock.getId());
    Assertions.assertEquals(stock, stockDeleted);
    var stockToTestVerify = StockServiceFixtures.buildStock(stock);
    Mockito.verify(stockServiceMock, Mockito.times(1))
        .delete(Mockito.eq(stockToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundStock() {
    var stock = StockServiceFixtures.buildStock(1L);
    Mockito.when(stockServiceMock.findById(stock.getId())).thenReturn(Optional.of(stock));
    var stockFound = stockController.findById(stock.getId());
    Assertions.assertEquals(stock, stockFound);
    var stockToTestVerify = StockServiceFixtures.buildStock(stock);
    Mockito.verify(stockServiceMock, Mockito.times(1))
        .findById(Mockito.eq(stockToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateStock() {
    var stockToUpdate = StockServiceFixtures.buildStock(1L);
    stockToUpdate.setName("Testing Stock fake");
    Mockito.when(stockServiceMock.update(stockToUpdate)).thenReturn(stockToUpdate);
    var stockToTestVerify = StockServiceFixtures.buildStock(stockToUpdate);
    var stockUpdated = stockController.update(stockToUpdate);
    Assertions.assertEquals(stockToUpdate, stockUpdated);
    Mockito.verify(stockServiceMock, Mockito.times(1)).update(Mockito.eq(stockToTestVerify));
  }
}
