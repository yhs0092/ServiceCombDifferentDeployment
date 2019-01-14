package com.github.yhs0092.javachassis.demo.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
public class HelloService {

  @RequestMapping(path = "hello", method = RequestMethod.GET)
  public String sayHello(@RequestParam(name = "name") String name) {
    return "Hello, " + name;
  }
}
