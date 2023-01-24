package com.diana.restaurant.util;

import com.diana.restaurant.entities.OrderDetailProduct;
import java.util.Optional;

public final class OrderDetailProductUtil {
  public static OrderDetailProduct fetchOrderDetailProduct(
      OrderDetailProduct target, OrderDetailProduct source) {
    BaseEntityUtil.fetchBaseEntity(target, source);
    target.setFinalProduct(
        Optional.ofNullable(source)
            .map(OrderDetailProduct::getFinalProduct)
            .orElse(target.getFinalProduct()));
    target.setProductName(
        Optional.ofNullable(source)
            .map(OrderDetailProduct::getProductName)
            .orElse(target.getProductName()));
    target.setProductPrice(
        Optional.ofNullable(source)
            .map(OrderDetailProduct::getProductPrice)
            .orElse(target.getProductPrice()));
    target.setProductMeasureUnit(
        Optional.ofNullable(source)
            .map(OrderDetailProduct::getProductMeasureUnit)
            .orElse(target.getProductMeasureUnit()));
    target.setOrderDetailTaxes(
        Optional.ofNullable(source)
            .map(OrderDetailProduct::getOrderDetailTaxes)
            .orElse(target.getOrderDetailTaxes()));
    target.setOrderDetail(
        Optional.ofNullable(source)
            .map(OrderDetailProduct::getOrderDetail)
            .orElse(target.getOrderDetail()));
    return target;
  }
}
