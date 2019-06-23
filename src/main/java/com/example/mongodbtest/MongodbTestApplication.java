package com.example.mongodbtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author aaron
 */
@SpringBootApplication
public class MongodbTestApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(MongodbTestApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
