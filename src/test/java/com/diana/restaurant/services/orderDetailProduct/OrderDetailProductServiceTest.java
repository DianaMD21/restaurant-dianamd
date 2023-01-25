package com.diana.restaurant.services.orderDetailProduct;

import com.diana.restaurant.entities.OrderDetailProduct;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.OrderDetailProductServiceImpl;
import com.diana.restaurant.util.fixtures.entities.OrderDetailProductFixtures;
import com.diana.restaurant.util.fixtures.services.OrderDetailProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDetailProductServiceTest {
  private OrderDetailProductServiceImpl orderDetailProductService;

  @BeforeEach
  public void setup() {
    orderDetailProductService =
        Ioc.getInstance().get(IocServices.ORDER_DETAIL_PRODUCT_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailProductService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderDetailProductService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    Assertions.assertEquals(
        newOrderDetailProduct,
        orderDetailProductService.findById(orderDetailProduct.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    orderDetailProductService.delete(newOrderDetailProduct.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> orderDetailProductService.findById(newOrderDetailProduct.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var orderDetailProduct = new OrderDetailProduct();
    Assertions.assertThrows(
        IdNullPointerException.class, () -> orderDetailProductService.update(orderDetailProduct));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(
        NullEntityException.class, () -> orderDetailProductService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var orderDetailProductExample = new OrderDetailProduct();
    orderDetailProductExample.setId(OrderDetailProductFixtures.FAKEID);
    var orderDetailProduct =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProductExample);
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> orderDetailProductService.update(orderDetailProduct));
  }

  @Test
  public void update_ShouldReturnUpdatedOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    var updatedOrderDetailProductExample = new OrderDetailProduct();
    updatedOrderDetailProductExample.setProductName("Testing Fake Name");
    var updatedOrderDetailProduct =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(updatedOrderDetailProductExample);
    updatedOrderDetailProduct.setId(orderDetailProduct.getId());
    Assertions.assertEquals(
        newOrderDetailProduct, orderDetailProductService.update(updatedOrderDetailProduct));
  }

  @Test
  public void insert_ShouldInsertOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    Assertions.assertEquals(
        orderDetailProduct, orderDetailProductService.insert(orderDetailProduct));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var orderDetailProduct = new OrderDetailProduct();
    Assertions.assertThrows(
        IdNullPointerException.class,
        () -> orderDetailProductService.delete(orderDetailProduct.getId()));
  }

  @Test
  public void delete_ShouldDeleteOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var newOrderDetailProduct = orderDetailProductService.insert(orderDetailProduct);
    Assertions.assertEquals(
        newOrderDetailProduct, orderDetailProductService.delete(orderDetailProduct.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfOrderDetailProducts() {
    var orderDetailProducts = OrderDetailProductServiceFixtures.buildOrderDetailProducts(3);
    orderDetailProducts.stream().forEach(orderDetailProductService::insert);
    Assertions.assertEquals(orderDetailProducts, orderDetailProductService.findAll());
  }
}
