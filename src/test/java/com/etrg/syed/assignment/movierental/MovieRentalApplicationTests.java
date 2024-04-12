package com.etrg.syed.assignment.movierental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MovieRentalApplicationTests {

  @Autowired private ApplicationContext context;

  @Test
  void contextLoads() {
    assertThat(context).isNotNull();
  }
}
