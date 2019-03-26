package com.uhasoft.demo.sentinel.flow.client;

import com.uhasoft.demo.sentinel.flow.client.handler.UserServiceRestTemplateHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.alibaba.sentinel.annotation.SentinelRestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.uhasoft.demo.sentinel.flow.client.api"})
public class ClientApplication {

  public static void main(String[] args) {
    System.setProperty("server.port", "8002");
    SpringApplication.run(ClientApplication.class, args);
  }

  @Bean
  @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = UserServiceRestTemplateHandler.class)
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
