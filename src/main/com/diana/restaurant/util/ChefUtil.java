package com.diana.restaurant.util;

import com.diana.restaurant.entities.Chef;

public final class ChefUtil {
  public static Chef fetchChef(Chef target, Chef source) {
    return (Chef) EmployeeUtil.fetchEmployee(target, source);
  }
}
