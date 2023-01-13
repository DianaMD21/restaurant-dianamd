package finalProduct;

import static org.junit.Assert.assertEquals;

import entities.FinalProduct;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.FinalProductServiceImpl;
import util.fixtures.FinalProductFixtures;
import util.fixtures.FinalProductServiceFixtures;

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
    assertEquals(newFinalProduct, finalProductService.findById(finalProduct.getId()));
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    var newFinalProduct = finalProductService.insert(finalProduct).get();
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
  public void update_ShouldReturnUpdatedObject() {
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
  public void insert_ShouldReturnOptionalOfFinalProduct() {
    var finalProduct = FinalProductServiceFixtures.buildFinalProduct();
    assertEquals(Optional.of(finalProduct), finalProductService.insert(finalProduct));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var finalProduct = new FinalProduct();
    finalProductService.delete(finalProduct.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
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
