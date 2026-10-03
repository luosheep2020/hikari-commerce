package com.hikaricommerce.mall;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class HikariCommerceApplication {

  public static void main(String[] args) {
    SpringApplication.run(HikariCommerceApplication.class, args);
  }
  @Bean
  ApplicationRunner checkConfig(Environment env) {
    return args -> {
      System.out.println("应用名称：" +
        env.getProperty("spring.application.name"));

      String password = env.getProperty("DB_PASSWORD");
      System.out.println("DB_PASSWORD 是否读取到：" +
        (password != null && !password.isBlank()));
    };
  }
}
