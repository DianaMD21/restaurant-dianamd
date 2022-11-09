package util.fixtures;

import entities.Chef;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public final class ChefServiceFixtures {
  public static final Long FAKEID = 10L;

  public static List<Chef> buildChefs(Integer size) {
    return LongStream.range(0, size)
        .mapToObj(ChefServiceFixtures::buildChef)
        .collect(Collectors.toList());
  }

  public static Chef buildChef() {
    return buildChef((Chef) null);
  }

  public static Chef buildChef(Long id) {
    var chefExample = new Chef();
    chefExample.setId(id);
    return buildChef(chefExample);
  }

  public static Chef buildChef(Chef chefExample) {
    var chef = new Chef();
    chef.setCode(Optional.ofNullable(chefExample).map(Chef::getCode).orElse("testing-code"));
    EmployeeFixtures.buildEmployee(chef, chefExample);
    return chef;
  }
}
