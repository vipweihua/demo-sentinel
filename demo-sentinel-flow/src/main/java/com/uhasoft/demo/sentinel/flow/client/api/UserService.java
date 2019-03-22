package com.uhasoft.demo.sentinel.flow.client.api;

import com.uhasoft.demo.sentinel.flow.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@FeignClient(name="server", url = "http://localhost:8002")
public interface UserService {

  @GetMapping("/{id}")
  User findById(@PathVariable("id") String id);
}
