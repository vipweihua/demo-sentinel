package com.uhasoft.demo.sentinel.flow.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.uhasoft.demo.sentinel.flow.server.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Weihua
 * @date 2019/3/22
 * 模拟服务端的Controller
 */
@RestController
public class ServerController {

  /**
   * 这里的blockHandler可以在本类中（默认，即不指定blockHandlerClass），
   * 也可以是来自于专门的异常处理类中，但需要指定blockHandlerClass
   * 两者的区别在于定义方法时是否需要static，这里不得不提OpenFeign的限流方式，
   * OpenFeign异常处理也需要指定handlerClass，但又因为必须继承API接口，所以不能static，所以只能在spring容器中初始化bean，
   * 以上听起来似乎挺有道理，但总觉得这里面太不规范，或者不统一
   * 个人感觉还不如都实例化在容器中，纯个人观点
   * SentinelResource注解上，如果注释块中的代码放开，即启动专门异常类处理，注释后则采用本类中由blockHandler指定的异常处理方法
   * @param id 用户ID
   * @return 用户对象
   */
  @GetMapping("/{id}")
  @SentinelResource(value = "findById", blockHandler = "handleFlowException"/*, blockHandlerClass = FlowExceptionHandler.class*/)
  public User findById(@PathVariable String id){
    if(id == null || id.trim().equals("")){
      return null;
    }
    return new User(id, "User" + id);
  }

  /**
   * 有时候想去看看指定的规则有没有加载进来，而在开发环境懒得去启动Dashboard查看该服务上的限流规则，不如这个方法快速有效
   * @return 该实例上的所有流控规则
   */
  @GetMapping("/rule")
  public List<FlowRule> showFlowRules(){
    return FlowRuleManager.getRules();
  }

  /**
   * 异常处理方法，blockHandlerClass未指定时启用本方法，blockHandlerClass指定时启用指定类中的对应方法
   * 本方法的定义规则为SentinelResource注解类所在方法入参和出参基本一致，唯一不一样的是入参多了BlockException
   * 当然在blockHandlerClass中处理时另需要增加static，很简单，但有点绕，不统一所致。
   * @param id findById方法中的入参
   * @param ex 所需要处理的异常类，本例是完全基于流控的，其实这里更希望指定的异常类是FlowException，
   *           就像SpringMVC中的ExceptionHandler，但是很遗憾，以为在sentinel-core中已经明确写死了该类必须是BlockException,
   *           代码位于AbstractSentinelAspectSupport类resolveBlockHandlerInternal方法中的parameterTypes参数
   * @return 同findById的出参
   */
  public User handleFlowException(String id, BlockException ex){
    System.out.println("Traffic is blocked!");
    return new User("000", "Error Handled In ServerController Handler");
  }
}
