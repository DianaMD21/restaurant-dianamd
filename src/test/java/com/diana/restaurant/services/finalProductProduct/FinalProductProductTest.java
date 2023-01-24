package com.diana.restaurant.services.finalProductProduct;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.FinalProductProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.FinalProductProductServiceImpl;
import com.diana.restaurant.util.fixtures.FinalProductProductFixtures;
import com.diana.restaurant.util.fixtures.FinalProductProductServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class FinalProductProductTest {
  private FinalProductProductServiceImpl finalProductProductService;

  @Before
  public void setup() {
    finalProductProductService =
        Ioc.getInstance().get(IocServices.FINAL_PRODUCT_PRODUCT_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    finalProductProductService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    finalProductProductService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    assertEquals(
        newFinalProductProduct,
        finalProductProductService.findById(finalProductProduct.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    finalProductProductService.delete(newFinalProductProduct.getId());
    finalProductProductService.findById(newFinalProductProduct.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var finalProductProduct = new FinalProductProduct();
    finalProductProductService.update(finalProductProduct);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    finalProductProductService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var finalProductProductExample = new FinalProductProduct();
    finalProductProductExample.setId(FinalProductProductFixtures.FAKEID);
    var finalProductProduct =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProductExample);
    finalProductProductService.update(finalProductProduct);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    var updatedFinalProductProductExample = new FinalProductProduct();
    updatedFinalProductProductExample.setQuantity(500.6);
    var updatedFinalProductProduct =
        FinalProductProductServiceFixtures.buildFinalProductProduct(
            updatedFinalProductProductExample);
    updatedFinalProductProduct.setId(finalProductProduct.getId());
    assertEquals(
        newFinalProductProduct, finalProductProductService.update(updatedFinalProductProduct));
  }

  @Test
  public void insert_ShouldReturnFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    assertEquals(finalProductProduct, finalProductProductService.insert(finalProductProduct));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var finalProductProduct = new FinalProductProduct();
    finalProductProductService.delete(finalProductProduct.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    assertEquals(
        newFinalProductProduct, finalProductProductService.delete(finalProductProduct.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfFinalProductProducts() {
    var finalProductProducts = FinalProductProductServiceFixtures.buildFinalProductProducts(3);
    finalProductProducts.stream().forEach(finalProductProductService::insert);
    assertEquals(finalProductProducts, finalProductProductService.findAll());
  }
}
