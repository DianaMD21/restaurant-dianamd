package com.diana.restaurant.controllers.orderDetailProduct;

import com.diana.restaurant.controllers.OrderDetailProductController;
import com.diana.restaurant.services.implementation.OrderDetailProductServiceImpl;
import com.diana.restaurant.services.interfaces.OrderDetailProductService;
import com.diana.restaurant.util.fixtures.services.OrderDetailProductServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailProductControllerIntegrationTest {
  private OrderDetailProductController orderDetailProductController;
  private OrderDetailProductService orderDetailProductServiceSpy;

  @BeforeEach
  public void setUp() {
    OrderDetailProductService orderDetailProductService = new OrderDetailProductServiceImpl();
    orderDetailProductServiceSpy = Mockito.spy(orderDetailProductService);
    orderDetailProductController = new OrderDetailProductController(orderDetailProductServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    orderDetailProductController.add(orderDetailProduct);
    var orderDetailProductFound = orderDetailProductController.findById(orderDetailProduct.getId());
    Assertions.assertEquals(orderDetailProductFound, orderDetailProduct);
    Mockito.verify(orderDetailProductServiceSpy, Mockito.times(1))
        .findById(Mockito.eq(orderDetailProduct.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllOrderDetailProducts() {
    var orderDetailProducts = OrderDetailProductServiceFixtures.buildOrderDetailProducts(3);
    orderDetailProducts.stream().forEach(orderDetailProductController::add);
    var orderDetailProductsFound = orderDetailProductController.getAll();
    Assertions.assertEquals(orderDetailProducts, orderDetailProductsFound);
  }

  @Test
  public void add_ShouldAddOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var orderDetailProductAdded = orderDetailProductController.add(orderDetailProduct);
    Assertions.assertEquals(orderDetailProductAdded, orderDetailProduct);
    Mockito.verify(orderDetailProductServiceSpy, Mockito.times(1))
        .add(Mockito.eq(orderDetailProduct));
  }

  @Test
  public void delete_ShouldDeleteOrderDetailProduct() {
    var orderDetailProducts = OrderDetailProductServiceFixtures.buildOrderDetailProducts(3);
    orderDetailProducts.stream().forEach(orderDetailProductController::add);
    var deletedOrderDetailProduct =
        orderDetailProductController.deleteById(orderDetailProducts.get(0).getId());
    var deletedOrderDetailProductToVerify =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProducts.get(0));
    Assertions.assertEquals(deletedOrderDetailProduct, orderDetailProducts.get(0));
    Mockito.verify(orderDetailProductServiceSpy, Mockito.times(1))
        .delete(Mockito.eq(deletedOrderDetailProductToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderDetailProduct() {
    var orderDetailProductToUpdate = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    orderDetailProductController.add(orderDetailProductToUpdate);
    orderDetailProductToUpdate.setProductName("Testing OrderDetailProduct fake");
    var orderDetailProductToTestVerify =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProductToUpdate);
    var updatedOrderDetailProduct = orderDetailProductController.update(orderDetailProductToUpdate);
    Assertions.assertEquals(updatedOrderDetailProduct, orderDetailProductToUpdate);
    Mockito.verify(orderDetailProductServiceSpy, Mockito.times(1))
        .update(Mockito.eq(orderDetailProductToTestVerify));
  }
}
