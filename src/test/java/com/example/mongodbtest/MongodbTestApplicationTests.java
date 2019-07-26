package com.example.mongodbtest;

import com.example.mongodbtest.dao.RouteRepository;
import com.example.mongodbtest.dao.Routes;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTestApplicationTests {

  @Autowired
  RouteRepository routeRepository;

  @Autowired
  MongoTemplate mongoTemplate;

  @Test
  public void contextLoads() {


    int count = 3;
    while (count-- > 0) {
      List<String> blacklist = new ArrayList<>();
      String id = RandomStringUtils.randomAlphabetic(4);
      routeRepository.insert(
          Routes.builder()
              .id(System.currentTimeMillis())
              .routeName("router-" + id)
              .routePath("/" + id)
              .filter("ipFilter")
              .blacklist(blacklist)
              .build()
      );
    }

    routeRepository.findAll().forEach(System.out::println);

  }

  @Test
  public void templateTest() {
    mongoTemplate.save(new Student());
  }

  @Document
  private class Student {

    private String name = "aaron";
    private int age = 18;
  }
}
