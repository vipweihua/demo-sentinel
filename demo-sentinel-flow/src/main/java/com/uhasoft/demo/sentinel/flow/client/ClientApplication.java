package com.uhasoft.demo.sentinel.flow.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.uhasoft.demo.sentinel.flow.client.api"})
public class ClientApplication {

  public static void main(String[] args) {
    System.setProperty("server.port", "8001");
    SpringApplication.run(ClientApplication.class, args);
  }

}
