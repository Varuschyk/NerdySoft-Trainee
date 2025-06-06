package com.nerdysoft.nerdysoftapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nerdysoft")
public class NerdySoftAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(NerdySoftAppApplication.class, args);
  }

}
