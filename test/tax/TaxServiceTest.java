package tax;

import static org.junit.Assert.assertEquals;

import entities.Tax;
import enums.IocServices;
import exceptions.services.EntityNotFoundException;
import exceptions.services.IdNullPointerException;
import exceptions.services.NullEntityException;
import ioc.Ioc;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import services.implementation.TaxServiceImpl;
import util.fixtures.TaxFixtures;
import util.fixtures.TaxServiceFixtures;

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
    assertEquals(newTax, taxService.findById(tax.getId()));
  }

  @Test(expected = EntityNotFoundException.class)
  public void findById_ShouldThrowEntityNotFoundException_WhenStatusIsDeleted() {
    var tax = TaxServiceFixtures.buildTax();
    var newTax = taxService.insert(tax).get();
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
  public void insert_ShouldReturnOptionalOfTax() {
    var tax = TaxServiceFixtures.buildTax();
    assertEquals(Optional.of(tax), taxService.insert(tax));
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
