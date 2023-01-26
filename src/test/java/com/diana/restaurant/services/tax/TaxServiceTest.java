package com.diana.restaurant.services.tax;

import com.diana.restaurant.entities.Tax;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.TaxServiceImpl;
import com.diana.restaurant.util.fixtures.entities.TaxFixtures;
import com.diana.restaurant.util.fixtures.services.TaxServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaxServiceTest {
  private TaxServiceImpl taxService;

  @BeforeEach
  public void setup() {
    taxService = Ioc.getInstance().get(IocServices.TAX_SERVICE_INSTANCE);
  }

  @Test()
  public void findById_ShouldThrowIdNullPointerException() {
    Assertions.assertThrows(IdNullPointerException.class, () -> taxService.findById(null));
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> taxService.findById(10L));
  }

  @Test
  public void findById_ShouldReturnOptionalOfTax() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.add(tax);
    Assertions.assertEquals(newTax, taxService.findById(tax.getId()).get());
  }

  @Test()
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.add(tax);
    taxService.delete(newTax.getId());
    Assertions.assertThrows(
        EntityNotFoundException.class, () -> taxService.findById(newTax.getId()));
  }

  @Test()
  public void update_ShouldThrowIdNullPointerException() {
    var tax = new Tax();
    Assertions.assertThrows(IdNullPointerException.class, () -> taxService.update(tax));
  }

  @Test()
  public void update_ShouldThrowNullEntityException() {
    Assertions.assertThrows(NullEntityException.class, () -> taxService.update(null));
  }

  @Test()
  public void update_ShouldThrowEntityNotFoundException() {
    var taxExample = new Tax();
    taxExample.setId(TaxFixtures.FAKEID);
    var tax = TaxServiceFixtures.buildTax(taxExample);
    Assertions.assertThrows(EntityNotFoundException.class, () -> taxService.update(tax));
  }

  @Test
  public void update_ShouldReturnUpdatedTax() {
    var tax = TaxServiceFixtures.buildTax();
    var updatedTaxExample = new Tax();
    updatedTaxExample.setName("Diana");
    updatedTaxExample.setTaxPercentage(5.6);
    var newTax = taxService.add(tax);
    var updatedTax = TaxServiceFixtures.buildTax(updatedTaxExample);
    updatedTax.setId(tax.getId());
    Assertions.assertEquals(newTax, taxService.update(updatedTax));
  }

  @Test
  public void add_ShouldAddTax() {
    var tax = TaxServiceFixtures.buildTax();
    Assertions.assertEquals(tax, taxService.add(tax));
  }

  @Test()
  public void delete_ShouldThrowIdNullPointerException() {
    var tax = new Tax();
    Assertions.assertThrows(IdNullPointerException.class, () -> taxService.delete(tax.getId()));
  }

  @Test
  public void delete_ShouldDeleteTax() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.add(tax);
    Assertions.assertEquals(newTax, taxService.delete(tax.getId()));
  }

  @Test
  public void getAll_ShouldReturnListOfTaxes() {
    var taxes = TaxServiceFixtures.buildTaxes(3);
    taxes.stream().forEach(taxService::add);
    Assertions.assertEquals(taxes, taxService.getAll());
  }
}
