package com.diana.restaurant.services.stock;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Stock;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.StockServiceImpl;
import com.diana.restaurant.util.fixtures.StockFixtures;
import com.diana.restaurant.util.fixtures.StockServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class StockServiceTest {
  private StockServiceImpl stockService;

  @Before
  public void setup() {
    stockService = Ioc.getInstance().get(IocServices.STOCK_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    stockService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    stockService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfStock() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    assertEquals(newStock, stockService.findById(stock.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    stockService.delete(newStock.getId());
    stockService.findById(newStock.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var stock = new Stock();
    stockService.update(stock);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    stockService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var stockExample = new Stock();
    stockExample.setId(StockFixtures.FAKEID);
    var stock = StockServiceFixtures.buildStock(stockExample);
    stockService.update(stock);
  }

  @Test
  public void update_ShouldReturnUpdatedStock() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    var updatedStockExample = new Stock();
    updatedStockExample.setName("Testing Fake Name");
    var updatedStock = StockServiceFixtures.buildStock(updatedStockExample);
    updatedStock.setId(stock.getId());
    assertEquals(newStock, stockService.update(updatedStock));
  }

  @Test
  public void insert_ShouldInsertStock() {
    var stock = StockServiceFixtures.buildStock();
    assertEquals(stock, stockService.insert(stock));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var stock = new Stock();
    stockService.delete(stock.getId());
  }

  @Test
  public void delete_ShouldDeleteStock() {
    var stock = StockServiceFixtures.buildStock();
    var newStock = stockService.insert(stock);
    assertEquals(newStock, stockService.delete(stock.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfStocks() {
    var stocks = StockServiceFixtures.buildStocks(3);
    stocks.stream().forEach(stockService::insert);
    assertEquals(stocks, stockService.findAll());
  }
}
