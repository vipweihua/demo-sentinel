package com.uhasoft.demo.sentinel.flow.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Weihua
 * @date: 2019/3/22
 * Here's the class description
 */
@SpringBootApplication
public class ServerApplication {

  public static void main(String[] args) {
    System.setProperty("server.port", "8002");
    SpringApplication.run(ServerApplication.class, args);
  }
}
