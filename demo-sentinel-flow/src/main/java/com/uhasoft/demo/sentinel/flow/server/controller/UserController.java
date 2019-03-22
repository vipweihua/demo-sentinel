package com.uhasoft.demo.sentinel.flow.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.uhasoft.demo.sentinel.flow.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@RestController
public class UserController {

  @GetMapping("/{id}")
  @SentinelResource(value = "findById", blockHandler = "handleFlowException")
  public User findById(@PathVariable String id){
    if(id == null || id.trim().equals("")){
      return null;
    }
    return new User(id, "User" + id);
  }

  public User handleFlowException(String id, FlowException ex){
    System.out.println("Traffic is blocked!");
    return null;
  }
}
