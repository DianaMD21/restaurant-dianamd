package com.diana.restaurant.services.stock;

import com.diana.restaurant.entities.Stock;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.StockServiceImpl;
import com.diana.restaurant.util.fixtures.entities.StockFixtures;
import com.diana.restaurant.util.fixtures.services.StockServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockServiceTest {
  private StockServiceImpl stockService;

  @BeforeEach
  public void setup() {
    stockService = Ioc.getInstance().get(IocServices.STOCK_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> stockService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> stockService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfStock() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    Assertions.assertEquals(newStock, stockService.findById(stock.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    stockService.delete(newStock.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> stockService.findById(newStock.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var stock = new Stock();
    Assertions.assertThrows(IdNullPointerException.class, () -> stockService.update(stock));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> stockService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var stockExample = new Stock();
    stockExample.setId(StockFixtures.FAKEID);
    var stock = StockServiceFixtures.buildStock(stockExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> stockService.update(stock));
  }

  @Test
  public void update_ShouldReturnUpdatedStock() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    var updatedStockExample = new Stock();
    updatedStockExample.setName("Testing Fake Name");
    var updatedStock = StockServiceFixtures.buildStock(updatedStockExample);
    updatedStock.setId(stock.getId());
    Assertions.assertEquals(newStock, stockService.update(updatedStock));
  }

  @Test
  public void insert_ShouldInsertStock() {
    var stock = StockServiceFixtures.buildStock();
    Assertions.assertEquals(stock, stockService.insert(stock));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var stock = new Stock();
    Assertions.assertThrows(IdNullPointerException.class, () -> stockService.delete(stock.getId()));
  }

  @Test
  public void delete_ShouldDeleteStock() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    Assertions.assertEquals(newStock, stockService.delete(stock.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfStocks() {
    var stocks = StockServiceFixtures.buildStocks(3);
    stocks.stream().forEach(stockService::insert);
    Assertions.assertEquals(stocks, stockService.findAll());
  }
}
