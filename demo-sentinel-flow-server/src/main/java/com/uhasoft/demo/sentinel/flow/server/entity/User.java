package com.uhasoft.demo.sentinel.flow.server.entity;

/**
 * @author Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
public class User {

  private String id;
  private String name;

  public User(){}

  public User(String id, String name){
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
