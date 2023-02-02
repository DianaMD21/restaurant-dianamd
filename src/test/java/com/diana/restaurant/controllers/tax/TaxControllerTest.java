package com.diana.restaurant.controllers.tax;

import com.diana.restaurant.controllers.TaxController;
import com.diana.restaurant.services.interfaces.TaxService;
import com.diana.restaurant.util.fixtures.services.TaxServiceFixtures;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaxControllerTest {
  private TaxController taxController;
  private TaxService taxServiceMock;

  @BeforeEach
  public void setup() {
    taxServiceMock = Mockito.mock(TaxService.class);
    taxController = new TaxController(taxServiceMock);
  }

  @Test
  public void getAll_ShouldReturnListOfTaxes() {
    var taxes = TaxServiceFixtures.buildTaxes(3);
    Mockito.when(taxServiceMock.getAll()).thenReturn(taxes);
    var taxesReturned = taxController.getAll();
    Assertions.assertEquals(taxes, taxesReturned);
    Mockito.verify(taxServiceMock, Mockito.times(1)).getAll();
  }

  @Test
  public void add_ShouldAddTax() {
    var tax = TaxServiceFixtures.buildTax();
    var expectedTax = TaxServiceFixtures.buildTax(1L);
    Mockito.when(taxServiceMock.add(Mockito.eq(tax))).thenReturn(expectedTax);
    var taxAdded = taxController.add(tax);
    Assertions.assertEquals(expectedTax, taxAdded);
    Mockito.verify(taxServiceMock, Mockito.times(1)).add(Mockito.eq(tax));
  }

  @Test()
  public void delete_ShouldDeleteTax() {
    var tax = TaxServiceFixtures.buildTax(1L);
    Mockito.when(taxServiceMock.delete(tax.getId())).thenReturn(tax);
    var taxDeleted = taxController.deleteById(tax.getId());
    Assertions.assertEquals(tax, taxDeleted);
    var taxToTestVerify = TaxServiceFixtures.buildTax(tax);
    Mockito.verify(taxServiceMock, Mockito.times(1)).delete(Mockito.eq(taxToTestVerify.getId()));
  }

  @Test
  public void findById_ShouldReturnFoundTax() {
    var tax = TaxServiceFixtures.buildTax(1L);
    Mockito.when(taxServiceMock.findById(tax.getId())).thenReturn(Optional.of(tax));
    var taxFound = taxController.findById(tax.getId());
    Assertions.assertEquals(tax, taxFound);
    var taxToTestVerify = TaxServiceFixtures.buildTax(tax);
    Mockito.verify(taxServiceMock, Mockito.times(1)).findById(Mockito.eq(taxToTestVerify.getId()));
  }

  @Test
  public void update_ShouldUpdateTax() {
    var taxToUpdate = TaxServiceFixtures.buildTax(1L);
    taxToUpdate.setName("Testing Tax fake");
    Mockito.when(taxServiceMock.update(taxToUpdate)).thenReturn(taxToUpdate);
    var taxToTestVerify = TaxServiceFixtures.buildTax(taxToUpdate);
    var taxUpdated = taxController.update(taxToUpdate);
    Assertions.assertEquals(taxToUpdate, taxUpdated);
    Mockito.verify(taxServiceMock, Mockito.times(1)).update(Mockito.eq(taxToTestVerify));
  }
}
