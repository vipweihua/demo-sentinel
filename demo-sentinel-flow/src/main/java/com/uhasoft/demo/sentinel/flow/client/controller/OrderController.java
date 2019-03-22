package com.uhasoft.demo.sentinel.flow.client.controller;

import com.uhasoft.demo.sentinel.flow.client.api.UserService;
import com.uhasoft.demo.sentinel.flow.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@RestController
public class OrderController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public String getName(@PathVariable String id){
    User user = userService.findById(id);
    if(user == null){
      return "User with id=" + id + " doesn\'t exist.";
    }
    return user.getName();
  }
}
