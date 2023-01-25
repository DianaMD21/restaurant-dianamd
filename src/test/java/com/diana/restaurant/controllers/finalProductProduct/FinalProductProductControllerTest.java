package com.diana.restaurant.controllers.finalProductProduct;

import com.diana.restaurant.controllers.FinalProductProductController;
import com.diana.restaurant.services.interfaces.FinalProductProductService;
import com.diana.restaurant.util.fixtures.services.FinalProductProductServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FinalProductProductControllerTest {
  private FinalProductProductController finalProductProductController;
  private FinalProductProductService finalProductProductServiceMock;

  @BeforeEach
  public void setup() {
    finalProductProductServiceMock = Mockito.mock(FinalProductProductService.class);
    finalProductProductController =
        new FinalProductProductController(finalProductProductServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfFinalProductProducts() {
    var finalProductProducts = FinalProductProductServiceFixtures.buildFinalProductProducts(3);
    Mockito.when(finalProductProductServiceMock.getAll()).thenReturn(finalProductProducts);
    var finalProductProductsReturned = finalProductProductController.getAll();
    Assertions.assertEquals(finalProductProducts, finalProductProductsReturned);
    Mockito.verify(finalProductProductServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct();
    var expectedFinalProductProduct =
        FinalProductProductServiceFixtures.buildFinalProductProduct(1L);
    Mockito.when(finalProductProductServiceMock.add(Mockito.eq(finalProductProduct)))
        .thenReturn(expectedFinalProductProduct);
    var finalProductProductAdded = finalProductProductController.add(finalProductProduct);
    Assertions.assertEquals(expectedFinalProductProduct, finalProductProductAdded);
    Mockito.verify(finalProductProductServiceMock, Mockito.times(1))
        .add(Mockito.eq(finalProductProduct));
  }

  @Test()
  public void delete_ShouldDeleteFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct(1L);
    Mockito.when(finalProductProductServiceMock.delete(finalProductProduct.getId()))
        .thenReturn(finalProductProduct);
    var finalProductProductDeleted =
        finalProductProductController.deleteById(finalProductProduct.getId());
    Assertions.assertEquals(finalProductProduct, finalProductProductDeleted);
    var finalProductProductToTestVerify =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProduct);
    Mockito.verify(finalProductProductServiceMock, Mockito.times(1))
        .delete(Mockito.eq(finalProductProductToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundFinalProductProduct() {
    var finalProductProduct = FinalProductProductServiceFixtures.buildFinalProductProduct(1L);
    Mockito.when(finalProductProductServiceMock.findById(finalProductProduct.getId()))
        .thenReturn(Optional.of(finalProductProduct));
    var finalProductProductFound =
        finalProductProductController.findById(finalProductProduct.getId());
    Assertions.assertEquals(finalProductProduct, finalProductProductFound);
    var finalProductProductToTestVerify =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProduct);
    Mockito.verify(finalProductProductServiceMock, Mockito.times(1))
        .findById(Mockito.eq(finalProductProductToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateFinalProductProduct() {
    var finalProductProductToUpdate =
        FinalProductProductServiceFixtures.buildFinalProductProduct(1L);
    finalProductProductToUpdate.setQuantity(23423.3);
    Mockito.when(finalProductProductServiceMock.update(finalProductProductToUpdate))
        .thenReturn(finalProductProductToUpdate);
    var finalProductProductToTestVerify =
        FinalProductProductServiceFixtures.buildFinalProductProduct(finalProductProductToUpdate);
    var finalProductProductUpdated =
        finalProductProductController.update(finalProductProductToUpdate);
    Assertions.assertEquals(finalProductProductToUpdate, finalProductProductUpdated);
    Mockito.verify(finalProductProductServiceMock, Mockito.times(1))
        .update(Mockito.eq(finalProductProductToTestVerify));
  }
}
