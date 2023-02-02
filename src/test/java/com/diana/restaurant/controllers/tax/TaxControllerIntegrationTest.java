package com.diana.restaurant.controllers.tax;

import com.diana.restaurant.controllers.TaxController;
import com.diana.restaurant.services.implementation.TaxServiceImpl;
import com.diana.restaurant.services.interfaces.TaxService;
import com.diana.restaurant.util.fixtures.services.TaxServiceFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaxControllerIntegrationTest {
  private TaxController taxController;
  private TaxService taxServiceSpy;

  @BeforeEach
  public void setUp() {
    TaxService taxService = new TaxServiceImpl();
    taxServiceSpy = Mockito.spy(taxService);
    taxController = new TaxController(taxServiceSpy);
  }

  @Test
  public void findById_ShouldReturnFoundTax() {
    var tax = TaxServiceFixtures.buildTax();
    taxController.add(tax);
    var taxFound = taxController.findById(tax.getId());
    Assertions.assertEquals(taxFound, tax);
    Mockito.verify(taxServiceSpy, Mockito.times(1)).findById(Mockito.eq(tax.getId()));
  }

  @Test
  public void getAll_ShouldReturnAllTaxes() {
    var taxes = TaxServiceFixtures.buildTaxes(3);
    taxes.stream().forEach(taxController::add);
    var taxesFound = taxController.getAll();
    Assertions.assertEquals(taxes, taxesFound);
  }

  @Test
  public void add_ShouldAddTax() {
    var tax = TaxServiceFixtures.buildTax();
    var taxAdded = taxController.add(tax);
    Assertions.assertEquals(taxAdded, tax);
    Mockito.verify(taxServiceSpy, Mockito.times(1)).add(Mockito.eq(tax));
  }

  @Test
  public void delete_ShouldDeleteTax() {
    var taxes = TaxServiceFixtures.buildTaxes(3);
    taxes.stream().forEach(taxController::add);
    var deletedTax = taxController.deleteById(taxes.get(0).getId());
    var deletedTaxToVerify = TaxServiceFixtures.buildTax(taxes.get(0));
    Assertions.assertEquals(deletedTax, taxes.get(0));
    Mockito.verify(taxServiceSpy, Mockito.times(1)).delete(Mockito.eq(deletedTaxToVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateTax() {
    var taxToUpdate = TaxServiceFixtures.buildTax();
    taxController.add(taxToUpdate);
    taxToUpdate.setName("Testing Tax fake");
    var taxToTestVerify = TaxServiceFixtures.buildTax(taxToUpdate);
    var updatedTax = taxController.update(taxToUpdate);
    Assertions.assertEquals(updatedTax, taxToUpdate);
    Mockito.verify(taxServiceSpy, Mockito.times(1)).update(Mockito.eq(taxToTestVerify));
  }
}
