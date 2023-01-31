package com.diana.restaurant.controllers.orderDetailProduct;

import com.diana.restaurant.controllers.OrderDetailProductController;
import com.diana.restaurant.services.interfaces.OrderDetailProductService;
import com.diana.restaurant.util.fixtures.services.OrderDetailProductServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderDetailProductControllerTest {
  private OrderDetailProductController orderDetailProductController;
  private OrderDetailProductService orderDetailProductServiceMock;

  @BeforeEach
  public void setup() {
    orderDetailProductServiceMock = Mockito.mock(OrderDetailProductService.class);
    orderDetailProductController = new OrderDetailProductController(orderDetailProductServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfOrderDetailProducts() {
    var orderDetailProducts = OrderDetailProductServiceFixtures.buildOrderDetailProducts(3);
    Mockito.when(orderDetailProductServiceMock.getAll()).thenReturn(orderDetailProducts);
    var orderDetailProductsReturned = orderDetailProductController.getAll();
    Assertions.assertEquals(orderDetailProducts, orderDetailProductsReturned);
    Mockito.verify(orderDetailProductServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct();
    var expectedOrderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct(1L);
    Mockito.when(orderDetailProductServiceMock.add(Mockito.eq(orderDetailProduct)))
        .thenReturn(expectedOrderDetailProduct);
    var orderDetailProductAdded = orderDetailProductController.add(orderDetailProduct);
    Assertions.assertEquals(expectedOrderDetailProduct, orderDetailProductAdded);
    Mockito.verify(orderDetailProductServiceMock, Mockito.times(1))
        .add(Mockito.eq(orderDetailProduct));
  }

  @Test()
  public void delete_ShouldDeleteOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct(1L);
    Mockito.when(orderDetailProductServiceMock.delete(orderDetailProduct.getId()))
        .thenReturn(orderDetailProduct);
    var orderDetailProductDeleted =
        orderDetailProductController.deleteById(orderDetailProduct.getId());
    Assertions.assertEquals(orderDetailProduct, orderDetailProductDeleted);
    var orderDetailProductToTestVerify =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProduct);
    Mockito.verify(orderDetailProductServiceMock, Mockito.times(1))
        .delete(Mockito.eq(orderDetailProductToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundOrderDetailProduct() {
    var orderDetailProduct = OrderDetailProductServiceFixtures.buildOrderDetailProduct(1L);
    Mockito.when(orderDetailProductServiceMock.findById(orderDetailProduct.getId()))
        .thenReturn(Optional.of(orderDetailProduct));
    var orderDetailProductFound = orderDetailProductController.findById(orderDetailProduct.getId());
    Assertions.assertEquals(orderDetailProduct, orderDetailProductFound);
    var orderDetailProductToTestVerify =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProduct);
    Mockito.verify(orderDetailProductServiceMock, Mockito.times(1))
        .findById(Mockito.eq(orderDetailProductToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateOrderDetailProduct() {
    var orderDetailProductToUpdate = OrderDetailProductServiceFixtures.buildOrderDetailProduct(1L);
    orderDetailProductToUpdate.setProductName("Testing OrderDetailProduct fake");
    Mockito.when(orderDetailProductServiceMock.update(orderDetailProductToUpdate))
        .thenReturn(orderDetailProductToUpdate);
    var orderDetailProductToTestVerify =
        OrderDetailProductServiceFixtures.buildOrderDetailProduct(orderDetailProductToUpdate);
    var orderDetailProductUpdated = orderDetailProductController.update(orderDetailProductToUpdate);
    Assertions.assertEquals(orderDetailProductToUpdate, orderDetailProductUpdated);
    Mockito.verify(orderDetailProductServiceMock, Mockito.times(1))
        .update(Mockito.eq(orderDetailProductToTestVerify));
  }
}
