package com.github.yhs0092.javachassis.demo.customservlet;

public class Person {
  private String name;

  private int age;

  public String getName() {
    return name;
  }

  public Person setName(String name) {
    this.name = name;
    return this;
  }

  public int getAge() {
    return age;
  }

  public Person setAge(int age) {
    this.age = age;
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Person{");
    sb.append("name='").append(name).append('\'');
    sb.append(", age=").append(age);
    sb.append('}');
    return sb.toString();
  }
}
