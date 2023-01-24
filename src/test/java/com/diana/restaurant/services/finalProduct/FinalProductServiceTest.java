package com.diana.restaurant.services.finalProduct;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.FinalProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.FinalProductServiceImpl;
import com.diana.restaurant.util.fixtures.FinalProductFixtures;
import com.diana.restaurant.util.fixtures.FinalProductServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class FinalProductServiceTest {
  private FinalProductServiceImpl finalProductService;

  @Before
  public void setup() {
    finalProductService = Ioc.getInstance().get(IocServices.FINAL_PRODUCT_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    finalProductService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    finalProductService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.insert(finalProduct);
    assertEquals(newFinalProduct, finalProductService.findById(finalProduct.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.insert(finalProduct);
    finalProductService.delete(newFinalProduct.getId());
    finalProductService.findById(newFinalProduct.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var finalProduct = new FinalProduct();
    finalProductService.update(finalProduct);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    finalProductService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var finalProductExample = new FinalProduct();
    finalProductExample.setId(FinalProductFixtures.FAKEID);
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct(finalProductExample);
    finalProductService.update(finalProduct);
  }

  @Test
  public void update_ShouldReturnUpdatedFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.insert(finalProduct);
    var updatedFinalProductExample = new FinalProduct();
    updatedFinalProductExample.setName("Diana");
    updatedFinalProductExample.setPrice(500.6);
    var updatedFinalProduct =
        FinalProductServiceFixtures.buildFinalProduct(updatedFinalProductExample);
    updatedFinalProduct.setId(finalProduct.getId());
    assertEquals(newFinalProduct, finalProductService.update(updatedFinalProduct));
  }

  @Test
  public void delete_ShouldInsertFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    assertEquals(finalProduct, finalProductService.insert(finalProduct));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var finalProduct = new FinalProduct();
    finalProductService.delete(finalProduct.getId());
  }

  @Test
  public void delete_ShouldDeleteFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.insert(finalProduct);
    assertEquals(newFinalProduct, finalProductService.delete(finalProduct.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfFinalProducts() {
    var finalProducts = FinalProductServiceFixtures.buildFinalProducts(3);
    finalProducts.stream().forEach(finalProductService::insert);
    assertEquals(finalProducts, finalProductService.findAll());
  }
}