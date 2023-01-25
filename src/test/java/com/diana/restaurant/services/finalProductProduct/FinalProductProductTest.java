package com.diana.restaurant.services.finalProductProduct;

import com.diana.restaurant.entities.FinalProductProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.FinalProductProductServiceImpl;
import com.diana.restaurant.util.fixtures.entities.FinalProductProductFixtures;
import com.diana.restaurant.util.fixtures.services.FinalProductProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinalProductProductTest {
  private FinalProductProductServiceImpl finalProductProductService;

  @BeforeEach
  public void setup() {
    finalProductProductService =
        Ioc.getInstance().get(IocServices.FINAL_PRODUCT_PRODUCT_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(
        IdNullPointerException.class, () -> finalProductProductService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> finalProductProductService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    Assertions.assertEquals(
        newFinalProductProduct,
        finalProductProductService.findById(finalProductProduct.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    finalProductProductService.delete(newFinalProductProduct.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> finalProductProductService.findById(newFinalProductProduct.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var finalProductProduct = new FinalProductProduct();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> finalProductProductService.update(finalProductProduct));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(
        NullEntityException.class, () -> finalProductProductService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var finalProductProductExample = new FinalProductProduct();
    finalProductProductExample.setId(FinalProductProductFixtures.FAKEID);
    var finalProductProduct =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProductExample);
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> finalProductProductService.update(finalProductProduct));
  }

  @Test
  public void update_ShouldReturnUpdatedFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    var updatedFinalProductProductExample = new FinalProductProduct();
    updatedFinalProductProductExample.setQuantity(500.6);
    var updatedFinalProductProduct =
        FinalProductProductServiceFixtures.buildFinalProductProduct(
            updatedFinalProductProductExample);
    updatedFinalProductProduct.setId(finalProductProduct.getId());
    Assertions.assertEquals(
        newFinalProductProduct, finalProductProductService.update(updatedFinalProductProduct));
  }

  @Test
  public void insert_ShouldInsertFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    Assertions.assertEquals(
        finalProductProduct, finalProductProductService.insert(finalProductProduct));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var finalProductProduct = new FinalProductProduct();
    Assertions.assertThrows(
        IdNullPointerException.class,
        () -> finalProductProductService.delete(finalProductProduct.getId()));
  }

  @Test
  public void delete_ShouldDeleteFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var newFinalProductProduct = finalProductProductService.insert(finalProductProduct);
    Assertions.assertEquals(
        newFinalProductProduct, finalProductProductService.delete(finalProductProduct.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfFinalProductProducts() {
    var finalProductProducts = FinalProductProductServiceFixtures.buildFinalProductProducts(3);
    finalProductProducts.stream().forEach(finalProductProductService::insert);
    Assertions.assertEquals(finalProductProducts, finalProductProductService.findAll());
  }
}
