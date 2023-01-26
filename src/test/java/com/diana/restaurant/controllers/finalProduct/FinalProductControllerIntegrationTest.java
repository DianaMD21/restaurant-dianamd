package com.diana.restaurant.controllers.finalProduct;

import com.diana.restaurant.controllers.FinalProductController;
import com.diana.restaurant.services.implementation.FinalProductServiceImpl;
import com.diana.restaurant.services.interfaces.FinalProductService;
import com.diana.restaurant.util.fixtures.services.FinalProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FinalProductControllerIntegrationTest {
  private FinalProductController finalProductController;
  private FinalProductService finalProductServiceSpy;

  @BeforeEach
  public void setUp() {
    FinalProductService finalProductService = new FinalProductServiceImpl();
    finalProductServiceSpy = Mockito.spy(finalProductService);
    finalProductController = new FinalProductController(finalProductServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    finalProductController.add(finalProduct);
    var finalProductFound = finalProductController.findById(finalProduct.getId());
    Assertions.assertEquals(finalProductFound, finalProduct);
    Mockito.verify(finalProductServiceSpy, Mockito.times(1))
        .findById(Mockito.eq(finalProduct.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllFinalProducts() {
    var finalProducts = FinalProductServiceFixtures.buildFinalProducts(3);
    finalProducts.stream().forEach(finalProductController::add);
    var finalProductsFound = finalProductController.getAll();
    Assertions.assertEquals(finalProducts, finalProductsFound);
  }

  @Test
  public void add_ShouldAddFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var finalProductAdded = finalProductController.add(finalProduct);
    Assertions.assertEquals(finalProductAdded, finalProduct);
    Mockito.verify(finalProductServiceSpy, Mockito.times(1)).add(Mockito.eq(finalProduct));
  }

  @Test
  public void delete_ShouldDeleteFinalProduct() {
    var finalProducts = FinalProductServiceFixtures.buildFinalProducts(3);
    finalProducts.stream().forEach(finalProductController::add);
    var deletedFinalProduct = finalProductController.deleteById(finalProducts.get(0).getId());
    var deletedFinalProductToVerify =
        FinalProductServiceFixtures.buildFinalProduct(finalProducts.get(0));
    Assertions.assertEquals(deletedFinalProduct, finalProducts.get(0));
    Mockito.verify(finalProductServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedFinalProductToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateFinalProduct() {
    var finalProductToUpdate = FinalProductServiceFixtures.buildFinalProduct();
    finalProductController.add(finalProductToUpdate);
    finalProductToUpdate.setName("DIANA MARIA");
    finalProductToUpdate.setPrice(234.3);
    var finalProductToTestVerify =
        FinalProductServiceFixtures.buildFinalProduct(finalProductToUpdate);
    var updatedFinalProduct = finalProductController.update(finalProductToUpdate);
    Assertions.assertEquals(updatedFinalProduct, finalProductToUpdate);
    Mockito.verify(finalProductServiceSpy, Mockito.times(1))
        .update(Mockito.eq(finalProductToTestVerify));
  }
}
