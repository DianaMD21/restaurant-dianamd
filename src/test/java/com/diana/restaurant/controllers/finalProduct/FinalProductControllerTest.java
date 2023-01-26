package com.diana.restaurant.controllers.finalProduct;

import com.diana.restaurant.controllers.FinalProductController;
import com.diana.restaurant.services.interfaces.FinalProductService;
import com.diana.restaurant.util.fixtures.services.FinalProductServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FinalProductControllerTest {
  private FinalProductController finalProductController;
  private FinalProductService finalProductServiceMock;

  @BeforeEach
  public void setup() {
    finalProductServiceMock = Mockito.mock(FinalProductService.class);
    finalProductController = new FinalProductController(finalProductServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfFinalProducts() {
    var finalProducts = FinalProductServiceFixtures.buildFinalProducts(3);
    Mockito.when(finalProductServiceMock.getAll()).thenReturn(finalProducts);
    var finalProductsReturned = finalProductController.getAll();
    Assertions.assertEquals(finalProducts, finalProductsReturned);
    Mockito.verify(finalProductServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var expectedFinalProduct = FinalProductServiceFixtures.buildFinalProduct(1L);
    Mockito.when(finalProductServiceMock.add(Mockito.eq(finalProduct)))
        .thenReturn(expectedFinalProduct);
    var finalProductAdded = finalProductController.add(finalProduct);
    Assertions.assertEquals(expectedFinalProduct, finalProductAdded);
    Mockito.verify(finalProductServiceMock, Mockito.times(1)).add(Mockito.eq(finalProduct));
  }

  @Test()
  public void delete_ShouldDeleteFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct(1L);
    Mockito.when(finalProductServiceMock.delete(finalProduct.getId())).thenReturn(finalProduct);
    var finalProductDeleted = finalProductController.deleteById(finalProduct.getId());
    Assertions.assertEquals(finalProduct, finalProductDeleted);
    var finalProductToTestVerify = FinalProductServiceFixtures.buildFinalProduct(finalProduct);
    Mockito.verify(finalProductServiceMock, Mockito.times(1))
        .delete(Mockito.eq(finalProductToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct(1L);
    Mockito.when(finalProductServiceMock.findById(finalProduct.getId()))
        .thenReturn(Optional.of(finalProduct));
    var finalProductFound = finalProductController.findById(finalProduct.getId());
    Assertions.assertEquals(finalProduct, finalProductFound);
    var finalProductToTestVerify = FinalProductServiceFixtures.buildFinalProduct(finalProduct);
    Mockito.verify(finalProductServiceMock, Mockito.times(1))
        .findById(Mockito.eq(finalProductToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateFinalProduct() {
    var finalProductToUpdate = FinalProductServiceFixtures.buildFinalProduct(1L);
    finalProductToUpdate.setName("Diana");
    finalProductToUpdate.setPrice(50.32);
    Mockito.when(finalProductServiceMock.update(finalProductToUpdate))
        .thenReturn(finalProductToUpdate);
    var finalProductToTestVerify =
        FinalProductServiceFixtures.buildFinalProduct(finalProductToUpdate);
    var finalProductUpdated = finalProductController.update(finalProductToUpdate);
    Assertions.assertEquals(finalProductToUpdate, finalProductUpdated);
    Mockito.verify(finalProductServiceMock, Mockito.times(1))
        .update(Mockito.eq(finalProductToTestVerify));
  }
}
