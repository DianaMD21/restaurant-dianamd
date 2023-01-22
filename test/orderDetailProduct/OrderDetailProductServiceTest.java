package orderDetailProduct;

import static org.junit.Assert.assertEquals;

import entities.OrderDetailProduct;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.OrderDetailProductServiceImpl;
import util.fixtures.OrderDetailProductFixtures;
import util.fixtures.OrderDetailProductServiceFixtures;

public class OrderDetailProductServiceTest {
  private OrderDetailProductServiceImpl orderDetailProductService;

  @Before
  public void setup() {
    orderDetailProductService =
        Ioc.getInstance().get(IocServices.ORDER_DETAIL_PRODUCT_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    orderDetailProductService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    orderDetailProductService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    assertEquals(
        newOrderDetailProduct, orderDetailProductService.findById(orderDetailProduct.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    orderDetailProductService.delete(newOrderDetailProduct.getId());
    orderDetailProductService.findById(newOrderDetailProduct.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var orderDetailProduct = new OrderDetailProduct();
    orderDetailProductService.update(orderDetailProduct);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    orderDetailProductService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var orderDetailProductExample = new OrderDetailProduct();
    orderDetailProductExample.setId(OrderDetailProductFixtures.FAKEID);
    var orderDetailProduct =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProductExample);
    orderDetailProductService.update(orderDetailProduct);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    var updatedOrderDetailProductExample = new OrderDetailProduct();
    updatedOrderDetailProductExample.setProductName("Testing Fake Name");
    var updatedOrderDetailProduct =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(updatedOrderDetailProductExample);
    updatedOrderDetailProduct.setId(orderDetailProduct.getId());
    assertEquals(
        newOrderDetailProduct, orderDetailProductService.update(updatedOrderDetailProduct));
  }

  @Test
  public void insert_ShouldReturnOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    assertEquals(
        orderDetailProduct, orderDetailProductService.insert(orderDetailProduct));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetailProduct = new OrderDetailProduct();
    orderDetailProductService.delete(orderDetailProduct.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    assertEquals(
        newOrderDetailProduct, orderDetailProductService.delete(orderDetailProduct.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrderDetailProducts() {
    var orderDetailProducts = OrderDetailProductServiceFixtures.buildOrderDetailProducts(3);
    orderDetailProducts.stream().forEach(orderDetailProductService::insert);
    assertEquals(orderDetailProducts, orderDetailProductService.findAll());
  }
}
