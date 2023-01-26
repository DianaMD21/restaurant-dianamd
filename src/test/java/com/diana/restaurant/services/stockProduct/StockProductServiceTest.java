package com.diana.restaurant.services.stockProduct;

import com.diana.restaurant.entities.StockProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.StockProductServiceImpl;
import com.diana.restaurant.util.fixtures.entities.StockProductFixtures;
import com.diana.restaurant.util.fixtures.services.StockProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockProductServiceTest {
  private StockProductServiceImpl stockProductService;

  @BeforeEach
  public void setup() {
    stockProductService = Ioc.getInstance().get(IocServices.STOCK_PRODUCT_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> stockProductService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> stockProductService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.add(stockProduct);
    Assertions.assertEquals(
        newStockProduct, stockProductService.findById(stockProduct.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.add(stockProduct);
    stockProductService.delete(newStockProduct.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> stockProductService.findById(newStockProduct.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var stockProduct = new StockProduct();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> stockProductService.update(stockProduct));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> stockProductService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var stockProductExample = new StockProduct();
    stockProductExample.setId(StockProductFixtures.FAKEID);
    var stockProduct = StockProductServiceFixtures.buildStockProduct(stockProductExample);
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> stockProductService.update(stockProduct));
  }

  @Test
  public void update_ShouldReturnUpdatedStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.add(stockProduct);
    var updatedStockProductExample = new StockProduct();
    updatedStockProductExample.setQuantity(500.6);
    var updatedStockProduct =
        StockProductServiceFixtures.buildStockProduct(updatedStockProductExample);
    updatedStockProduct.setId(stockProduct.getId());
    Assertions.assertEquals(newStockProduct, stockProductService.update(updatedStockProduct));
  }

  @Test
  public void add_ShouldAddStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    Assertions.assertEquals(stockProduct, stockProductService.add(stockProduct));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var stockProduct = new StockProduct();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> stockProductService.delete(stockProduct.getId()));
  }

  @Test
  public void delete_ShouldDeleteStockProduct() {
    var stockProduct = StockProductServiceFixtures.buildStockProduct();
    var newStockProduct = stockProductService.add(stockProduct);
    Assertions.assertEquals(newStockProduct, stockProductService.delete(stockProduct.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfStockProducts() {
    var stockProducts = StockProductServiceFixtures.buildStockProducts(3);
    stockProducts.stream().forEach(stockProductService::add);
    Assertions.assertEquals(stockProducts, stockProductService.getAll());
  }
}
