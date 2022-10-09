package entities;

public abstract class DefaultTaxHistory extends BaseEntity<Long> {
  private Tax tax;
  private String taxName;
  private Double taxPercentage;

  public DefaultTaxHistory() {}

  public Tax getTax() {
    return tax;
  }

  public void setTax(Tax tax) {
    this.tax = tax;
  }

  public String getTaxName() {
    return taxName;
  }

  public void setTaxName(String taxName) {
    this.taxName = taxName;
  }

  public Double getTaxPercentage() {
    return taxPercentage;
  }

  public void setTaxPercentage(Double taxPercentage) {
    this.taxPercentage = taxPercentage;
  }
}
