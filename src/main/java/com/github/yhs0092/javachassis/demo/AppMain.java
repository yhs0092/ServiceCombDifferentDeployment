package com.github.yhs0092.javachassis.demo;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class AppMain {
  public static void main(String[] args) {
    SpringApplication.run(AppMain.class, args);
  }
}
