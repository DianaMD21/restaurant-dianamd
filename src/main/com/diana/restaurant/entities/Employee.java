package com.diana.restaurant.entities;

public class Employee extends User {
  private String code;

  public Employee() {
    super();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
