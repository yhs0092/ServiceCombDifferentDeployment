package com.github.yhs0092.javachassis.demo.customservlet;

import javax.ws.rs.core.Response.Status;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.swagger.invocation.exception.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId = "person")
@RequestMapping("/testCustomServlet/person")
public class MyTestServlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyTestServlet.class);

  @Autowired
  private PersonManager personManager;

  public MyTestServlet() {
    LOGGER.info("MyTestServlet constructed!");
  }

  @GetMapping
  public Person doGet(@RequestParam("name") String name) {
    LOGGER.info("doGet is called, name = [{}]", name);

    Person person = personManager.findPerson(name);
    if (null == person) {
      responsePersonNotFound(name);
    }
    return person;
  }

  @PutMapping
  public CommonResponse doPut(@RequestBody Person person) {
    LOGGER.info("doPut is called, person = [{}]", person);

    personManager.addPerson(person);
    return new CommonResponse("OK");
  }

  @DeleteMapping
  public Person doDelete(@RequestParam("name") String name) {
    LOGGER.info("doDelete is called, name = [{}]", name);

    Person person = personManager.deletePerson(name);
    if (null == person) {
      responsePersonNotFound(name);
    }
    return person;
  }

  private void responsePersonNotFound(String name) {
    throw new InvocationException(Status.NOT_FOUND, new CommonResponse("there is no person named " + name));
  }
}
