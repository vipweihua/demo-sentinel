package com.uhasoft.demo.sentinel.flow.server.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.uhasoft.demo.sentinel.flow.server.entity.User;

/**
 * 异常处理类，这里的handleFlowException方法必须是static，否则会报方法找不到的运行时错误，
 * 具体可阅读位于AbstractSentinelAspectSupport类中的源码
 * @author Weihua
 * @date: 2019/3/25
 * 异常处理类
 */
public class FlowExceptionHandler {

  /**
   * 见ServerController类中的同方法名注释
   */
  public static User handleFlowException(String id, BlockException ex){
    System.out.println("Traffic is blocked!");
    return new User("000", "Error Handled In FlowExceptionHandler");
  }
}
