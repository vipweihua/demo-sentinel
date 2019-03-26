package com.uhasoft.demo.sentinel.flow.client.controller;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.uhasoft.demo.sentinel.flow.client.api.UserService;
import com.uhasoft.demo.sentinel.flow.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Weihua
 * @date 2019/3/22
 * Here's the class description
 */
@RestController
public class ClientController {

  /**调用像本地Service一样，但事实上是有Feign包装过的*/
  @Autowired
  private UserService userService;

  /**
   * 这里的RestTemplate已经被Sentinel拦截，可以应用限流规则
   */
  @Autowired
  private RestTemplate restTemplate;

  /**
   * 虽为Client，但事实上也是服务端，客户端是针对服务间调用时的调用方而言的，
   * 调用方针对提供方的服务能力而做的限流控制，即为客户端限流，
   * 事实上限制的是一个客户端实例（实例级别）到服务端服务（服务级别）的流量，
   * 当客户端流量不均衡，如何防止错杀，又能最大能力服务好，感觉有问题，但目前不确认。
   * 后注：曾咨询过SCA的sentinel开发，回答是可以采用集群流控，
   * 那问题又来了，客户端的流控的应用场景是哪些呢？似乎实际意义不大。
   *
   * @param id 用户ID
   * @return 根据获取的用户对象，返回名字，否则返回错误消息
   */
  @GetMapping("/{id}")
  public String getName(@PathVariable String id){
    User user = userService.findById(id);
    if(user == null){
      return "User with id=" + id + " doesn\'t exist.";
    }
    return user.getName();
  }

  /**
   * 采用RestTemplate的客户端流控
   * 该方法在0.2.2版本中还存在一个比较重要的问题，几乎达到不可用的地步：
   * 如下调用中存在一个url参数，而这个参数无法被SentinelProtectInterceptor获取到，只是一个具体的参数，
   * 针对这中情况，导致规则就无法设置。
   * @param id 用户ID
   * @return 根据获取的用户对象，返回名字，否则返回错误消息
   */
  @GetMapping("/{id}/rest")
  public String getNameByRestTemplate(@PathVariable String id){

    //这里也存在一个bug，如果返回值是User.class，而不是String.class，当流控规则触发时，因没有User信息而导致json转换失败抛异常
    //几乎无法使用。。。。。。。。。
    String user = restTemplate.getForEntity("http://localhost:8001/{id}", String.class, Collections.singletonMap("id", id)).getBody();

    System.out.println(user);
    return user;
  }


  /**
   * 有时候想去看看指定的规则有没有加载进来，而在开发环境懒得去启动Dashboard查看该服务上的限流规则，不如这个方法快速有效
   * @return 该实例上的所有流控规则
   */
  @GetMapping("/rule")
  public List<FlowRule> showFlowRules(){
    return FlowRuleManager.getRules();
  }
}
