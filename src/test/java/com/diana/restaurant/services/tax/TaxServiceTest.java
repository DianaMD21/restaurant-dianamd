package com.diana.restaurant.services.tax;

import static org.junit.Assert.assertEquals;

import com.diana.restaurant.entities.Tax;
import com.diana.restaurant.enums.IocServices;
import com.diana.restaurant.exceptions.services.EntityNotFoundException;
import com.diana.restaurant.exceptions.services.IdNullPointerException;
import com.diana.restaurant.exceptions.services.NullEntityException;
import com.diana.restaurant.ioc.Ioc;
import com.diana.restaurant.services.implementation.TaxServiceImpl;
import com.diana.restaurant.util.fixtures.TaxFixtures;
import com.diana.restaurant.util.fixtures.TaxServiceFixtures;
import org.junit.Before;
import org.junit.Test;

public class TaxServiceTest {
  private TaxServiceImpl taxService;

  @Before
  public void setup() {
    taxService = Ioc.getInstance().get(IocServices.TAX_SERVICE_INSTANCE);
  }

  @Test(expected = IdNullPointerException.class)
  public void findById_ShouldThrowIdNullPointerException() {
    taxService.findById(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException() {
    taxService.findById(10L);
  }

  @Test
  public void findById_ShouldReturnOptionalOfTax() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.insert(tax);
    assertEquals(newTax, taxService.findById(tax.getId()).get());
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.insert(tax);
    taxService.delete(newTax.getId());
    taxService.findById(newTax.getId());
  }

  @Test(expected = IdNullPointerException.class)
  public void update_ShouldThrowIdNullPointerException() {
    var tax = new Tax();
    taxService.update(tax);
  }

  @Test(expected = NullEntityException.class)
  public void update_ShouldThrowNullEntityException() {
    taxService.update(null);
  }

  @Test(expected = EntityNotFoundException.class)
  public void update_ShouldThrowEntityNotFoundException() {
    var taxExample = new Tax();
    taxExample.setId(TaxFixtures.FAKEID);
    var tax = TaxServiceFixtures.buildTax(taxExample);
    taxService.update(tax);
  }

  @Test
  public void update_ShouldReturnUpdatedObject() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.insert(tax);
    var updatedTaxExample = new Tax();
    updatedTaxExample.setName("Diana");
    updatedTaxExample.setTaxPercentage(5.6);
    var updatedTax = TaxServiceFixtures.buildTax(updatedTaxExample);
    updatedTax.setId(tax.getId());
    assertEquals(newTax, taxService.update(updatedTax));
  }

  @Test
  public void insert_ShouldReturnTax() {
    var tax = TaxServiceFixtures.buildTax();
    assertEquals(tax, taxService.insert(tax));
  }

  @Test(expected = IdNullPointerException.class)
  public void delete_ShouldThrowIdNullPointerException() {
    var tax = new Tax();
    taxService.delete(tax.getId());
  }

  @Test
  public void delete_ShouldReturnDeletedValue() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.insert(tax);
    assertEquals(newTax, taxService.delete(tax.getId()));
  }

  @Test
  public void findAll_ShouldReturnListOfTaxes() {
    var taxes = TaxServiceFixtures.buildTaxes(3);
    taxes.stream().forEach(taxService::insert);
    assertEquals(taxes, taxService.findAll());
  }
}
