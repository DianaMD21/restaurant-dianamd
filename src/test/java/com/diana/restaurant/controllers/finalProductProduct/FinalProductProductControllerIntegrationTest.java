package com.diana.restaurant.controllers.finalProductProduct;

import com.diana.restaurant.controllers.FinalProductProductController;
import com.diana.restaurant.services.implementation.FinalProductProductServiceImpl;
import com.diana.restaurant.services.interfaces.FinalProductProductService;
import com.diana.restaurant.util.fixtures.services.FinalProductProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FinalProductProductControllerIntegrationTest {
  private FinalProductProductController finalProductProductController;
  private FinalProductProductService finalProductProductServiceSpy;

  @BeforeEach
  public void setUp() {
    FinalProductProductService finalProductProductService = new FinalProductProductServiceImpl();
    finalProductProductServiceSpy = Mockito.spy(finalProductProductService);
    finalProductProductController =
        new FinalProductProductController(finalProductProductServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    finalProductProductController.add(finalProductProduct);
    var finalProductProductFound =
        finalProductProductController.findById(finalProductProduct.getId());
    Assertions.assertEquals(finalProductProductFound, finalProductProduct);
    Mockito.verify(finalProductProductServiceSpy, Mockito.times(1))
        .findById(Mockito.eq(finalProductProduct.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllFinalProductProducts() {
    var finalProductProducts = FinalProductProductServiceFixtures.buildFinalProductProducts(3);
    finalProductProducts.stream().forEach(finalProductProductController::add);
    var finalProductProductsFound = finalProductProductController.getAll();
    Assertions.assertEquals(finalProductProducts, finalProductProductsFound);
  }

  @Test
  public void add_ShouldAddFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var finalProductProductAdded = finalProductProductController.add(finalProductProduct);
    Assertions.assertEquals(finalProductProductAdded, finalProductProduct);
    Mockito.verify(finalProductProductServiceSpy, Mockito.times(1))
        .add(Mockito.eq(finalProductProduct));
  }

  @Test
  public void delete_ShouldDeleteFinalProductProduct() {
    var finalProductProducts = FinalProductProductServiceFixtures.buildFinalProductProducts(3);
    finalProductProducts.stream().forEach(finalProductProductController::add);
    var deletedFinalProductProduct =
        finalProductProductController.deleteById(finalProductProducts.get(0).getId());
    var deletedFinalProductProductToVerify =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProducts.get(0));
    Assertions.assertEquals(deletedFinalProductProduct, finalProductProducts.get(0));
    Mockito.verify(finalProductProductServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedFinalProductProductToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateFinalProductProduct() {
    var finalProductProductToUpdate = FinalProductProductServiceFixtures.buildFinalProductProduct();
    finalProductProductController.add(finalProductProductToUpdate);
    finalProductProductToUpdate.setQuantity(234.3);
    var finalProductProductToTestVerify =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProductToUpdate);
    var updatedFinalProductProduct =
        finalProductProductController.update(finalProductProductToUpdate);
    Assertions.assertEquals(updatedFinalProductProduct, finalProductProductToUpdate);
    Mockito.verify(finalProductProductServiceSpy, Mockito.times(1))
        .update(Mockito.eq(finalProductProductToTestVerify));
  }
}
