package com.uhasoft.demo.sentinel.flow.client.api;

import com.uhasoft.demo.sentinel.flow.client.entity.User;
import com.uhasoft.demo.sentinel.flow.client.handler.UserServiceFeignHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign的相关内容可参看专门的文档，本例只是用了最简单的ip+端口的方式
 * @author Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@FeignClient(name="server", url = "http://localhost:8001", fallback = UserServiceFeignHandler.class)
public interface UserService {

  /**
   * 客户端的限流代码侵入性非常小，因为不需要指定SentinelResource，
   * 流控规则的资源名默认为<HttpMethod>:http://<ServiceName>/<URL>的格式
   * 如本例比较特殊，没有使用注册中心，也没有使用负载均衡，直接使用了URL，
   * 所以资源名为GET:http://localhost:8001/{id}，如果使用注册中心的情况，
   * 可能为GET:http://server/{id}
   * @param id 用户ID
   * @return 用户对象
   */
  @GetMapping("/{id}")
  User findById(@PathVariable("id") String id);
}
