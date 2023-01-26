package com.diana.restaurant.services.finalProduct;

import com.diana.restaurant.entities.FinalProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.FinalProductServiceImpl;
import com.diana.restaurant.util.fixtures.entities.FinalProductFixtures;
import com.diana.restaurant.util.fixtures.services.FinalProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinalProductServiceTest {
  private FinalProductServiceImpl finalProductService;

  @BeforeEach
  public void setup() {
    finalProductService = Ioc.getInstance().get(IocServices.FINAL_PRODUCT_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> finalProductService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> finalProductService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.add(finalProduct);
    Assertions.assertEquals(
        newFinalProduct, finalProductService.findById(finalProduct.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.add(finalProduct);
    finalProductService.delete(newFinalProduct.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> finalProductService.findById(newFinalProduct.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var finalProduct = new FinalProduct();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> finalProductService.update(finalProduct));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> finalProductService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var finalProductExample = new FinalProduct();
    finalProductExample.setId(FinalProductFixtures.FAKEID);
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct(finalProductExample);
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> finalProductService.update(finalProduct));
  }

  @Test
  public void update_ShouldReturnUpdatedFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var updatedFinalProductExample = new FinalProduct();
    updatedFinalProductExample.setName("Diana");
    updatedFinalProductExample.setPrice(500.6);
    var newFinalProduct = finalProductService.add(finalProduct);
    var updatedFinalProduct =
        FinalProductServiceFixtures.buildFinalProduct(updatedFinalProductExample);
    updatedFinalProduct.setId(finalProduct.getId());
    Assertions.assertEquals(newFinalProduct, finalProductService.update(updatedFinalProduct));
  }

  @Test
  public void delete_ShouldAddFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    Assertions.assertEquals(finalProduct, finalProductService.add(finalProduct));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var finalProduct = new FinalProduct();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> finalProductService.delete(finalProduct.getId()));
  }

  @Test
  public void delete_ShouldDeleteFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.add(finalProduct);
    Assertions.assertEquals(newFinalProduct, finalProductService.delete(finalProduct.getId()));
  }

  @Test
  public void add_ShouldAddClient() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    Assertions.assertEquals(finalProduct, finalProductService.add(finalProduct));
  }

  @Test
  public void getAll_ShouldReturnListOfFinalProducts() {
    var finalProducts = FinalProductServiceFixtures.buildFinalProducts(3);
    finalProducts.stream().forEach(finalProductService::add);
    Assertions.assertEquals(finalProducts, finalProductService.getAll());
  }
}
