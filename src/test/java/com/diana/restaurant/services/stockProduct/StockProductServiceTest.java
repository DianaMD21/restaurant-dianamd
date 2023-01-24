package com.diana.restaurant.services.stockProduct;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.StockProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.StockProductServiceImpl;
import com.diana.restaurant.util.fixtures.StockProductFixtures;
import com.diana.restaurant.util.fixtures.StockProductServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class StockProductServiceTest {
  private StockProductServiceImpl stockProductService;

  @Before
  public void setup() {
    stockProductService = Ioc.getInstance().get(IocServices.STOCK_PRODUCT_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    stockProductService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    stockProductService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.insert(stockProduct);
    assertEquals(newStockProduct, stockProductService.findById(stockProduct.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.insert(stockProduct);
    stockProductService.delete(newStockProduct.getId());
    stockProductService.findById(newStockProduct.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var stockProduct = new StockProduct();
    stockProductService.update(stockProduct);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    stockProductService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var stockProductExample = new StockProduct();
    stockProductExample.setId(StockProductFixtures.FAKEID);
    var stockProduct = StockProductServiceFixtures.buildStockProduct(stockProductExample);
    stockProductService.update(stockProduct);
  }

  @Test
  public void update_ShouldReturnUpdatedStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.insert(stockProduct);
    var updatedStockProductExample = new StockProduct();
    updatedStockProductExample.setQuantity(500.6);
    var updatedStockProduct =
        StockProductServiceFixtures.buildStockProduct(updatedStockProductExample);
    updatedStockProduct.setId(stockProduct.getId());
    assertEquals(newStockProduct, stockProductService.update(updatedStockProduct));
  }

  @Test
  public void insert_ShouldInsertStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    assertEquals(stockProduct, stockProductService.insert(stockProduct));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var stockProduct = new StockProduct();
    stockProductService.delete(stockProduct.getId());
  }

  @Test
  public void delete_ShouldDeleteStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.insert(stockProduct);
    assertEquals(newStockProduct, stockProductService.delete(stockProduct.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfStockProducts() {
    var stockProducts = StockProductServiceFixtures.buildStockProducts(3);
    stockProducts.stream().forEach(stockProductService::insert);
    assertEquals(stockProducts, stockProductService.findAll());
  }
}