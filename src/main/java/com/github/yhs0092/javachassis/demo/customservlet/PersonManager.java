package com.github.yhs0092.javachassis.demo.customservlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PersonManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonManager.class);

  private Map<String, Person> personMap = new ConcurrentHashMap<>();

  public void addPerson(Person person) {
    LOGGER.info("add a person {}", person);
    if (StringUtils.isEmpty(person.getName())) {
      throw new IllegalArgumentException("person name is required!");
    }
    if (person.getAge() < 0) {
      throw new IllegalArgumentException("person age must be positive!");
    }
    personMap.put(person.getName(), person);
  }

  public Person findPerson(String name) {
    LOGGER.info("find a person named {}", name);
    if (null == name) {
      return null;
    }
    return personMap.get(name);
  }

  public Person deletePerson(String name) {
    LOGGER.info("delete a person named {}", name);
    return personMap.remove(name);
  }
}
