package com.diana.restaurant.entities;

public class Client extends User {
  private String code;

  public Client() {
    super();
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}