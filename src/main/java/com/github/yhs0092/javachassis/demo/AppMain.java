package com.github.yhs0092.javachassis.demo;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableServiceComb
@ServletComponentScan // 启用自定义servlet
public class AppMain {
  public static void main(String[] args) {
    SpringApplication.run(AppMain.class, args);
  }
}
