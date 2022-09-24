package entities;

import enums.MeasureUnitEnum;

import java.util.ArrayList;
import java.util.List;

public class Product extends BaseEntity<Long> {
  private String name;
  private Double price;
  private MeasureUnitEnum measureUnitEnum;
  private List<FinalProductProduct> finalProductProducts;
  private List<Tax> taxes;

  public Product() {
    this.taxes = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public MeasureUnitEnum getMeasureUnit() {
    return measureUnitEnum;
  }

  public void setMeasureUnit(MeasureUnitEnum measureUnitEnum) {
    this.measureUnitEnum = measureUnitEnum;
  }

  public List<FinalProductProduct> getFinalProductProducts() {
    return finalProductProducts;
  }

  public void setFinalProductProducts(List<FinalProductProduct> finalProductProducts) {
    this.finalProductProducts = finalProductProducts;
  }

  public List<Tax> getTaxes() {
    return taxes;
  }

  public void setTaxes(List<Tax> taxes) {
    this.taxes = taxes;
  }
}
