package com.uhasoft.demo.sentinel.flow.client.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.cloud.alibaba.sentinel.rest.SentinelClientHttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @author: Weihua
 * @date: 2019/3/25
 * Here's the class description
 */
public class UserServiceRestTemplateHandler {

  public static SentinelClientHttpResponse handleException(HttpRequest request,
                                                           byte[] body, ClientHttpRequestExecution execution, BlockException ex) {
    System.out.println("Oops: " + ex.getClass().getCanonicalName());
    return new SentinelClientHttpResponse("custom block info");
  }

}