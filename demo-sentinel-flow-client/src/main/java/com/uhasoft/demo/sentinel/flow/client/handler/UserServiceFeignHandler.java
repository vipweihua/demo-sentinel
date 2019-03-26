package com.uhasoft.demo.sentinel.flow.client.handler;

import com.uhasoft.demo.sentinel.flow.client.api.UserService;
import com.uhasoft.demo.sentinel.flow.client.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author: Weihua
 * @date: 2019/3/25
 * Here's the class description
 */
@Component
public class UserServiceFeignHandler implements UserService {

  public User findById(String id) {
    return new User("000", "Feign client flow control triggered.");
  }
}
