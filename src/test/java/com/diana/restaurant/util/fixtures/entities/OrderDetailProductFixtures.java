package com.diana.restaurant.util.fixtures.entities;

import com.diana.restaurant.entities.OrderDetailProduct;
import com.diana.restaurant.enums.MeasureUnitEnum;
import com.diana.restaurant.util.fixtures.services.FinalProductServiceFixtures;
import java.util.Optional;

public final class OrderDetailProductFixtures {
  public static final Long FAKEID = 10L;

  public static OrderDetailProduct buildOrderDetailProduct(
      OrderDetailProduct orderDetailProduct, OrderDetailProduct orderDetailProductExample) {
    BaseEntityFixtures.buildBaseEntity(orderDetailProduct, orderDetailProductExample);
    orderDetailProduct.setFinalProduct(
        Optional.ofNullable(orderDetailProductExample)
            .map(OrderDetailProduct::getFinalProduct)
            .orElse(FinalProductServiceFixtures.buildFinalProduct()));
    orderDetailProduct.setProductName(
        Optional.ofNullable(orderDetailProductExample)
            .map(OrderDetailProduct::getProductName)
            .orElse("testing-product-name"));
    orderDetailProduct.setProductPrice(
        Optional.ofNullable(orderDetailProductExample)
            .map(OrderDetailProduct::getProductPrice)
            .orElse(89.45));
    orderDetailProduct.setProductMeasureUnit(
        Optional.ofNullable(orderDetailProductExample)
            .map(OrderDetailProduct::getProductMeasureUnit)
            .orElse(MeasureUnitEnum.POUNDS));
    orderDetailProduct.setOrderDetailTaxes(
        Optional.ofNullable(orderDetailProductExample)
            .map(OrderDetailProduct::getOrderDetailTaxes)
            .orElse(null));
    orderDetailProduct.setOrderDetail(
        Optional.ofNullable(orderDetailProductExample)
            .map(OrderDetailProduct::getOrderDetail)
            .orElse(OrderDetailServiceFixtures.buildOrderDetail()));
    return orderDetailProduct;
  }
}
