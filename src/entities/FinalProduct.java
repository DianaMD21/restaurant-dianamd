package entities;

import java.util.List;

public class FinalProduct extends Product {
  private List<FinalProductProduct> finalProductProducts;

  public FinalProduct() {}

  public List<FinalProductProduct> getFinalProductProducts() {
    return finalProductProducts;
  }

  public void setFinalProductProducts(List<FinalProductProduct> finalProductProducts) {
    this.finalProductProducts = finalProductProducts;
  }
}
