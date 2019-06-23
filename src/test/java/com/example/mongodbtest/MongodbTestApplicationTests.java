package com.example.mongodbtest;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTestApplicationTests {

  @Autowired
  RouteRepository routeRepository;

  @Test
  public void contextLoads() {
    List<String> blacklist = new ArrayList<>(3);
    blacklist.add("127.0.0.1");
    blacklist.add("192.168.1.1");

    Routes rs = routeRepository.insert(
        Routes.builder()
            .id(System.currentTimeMillis())
            .routeName("routerA01")
            .routePath("/A01")
            .blacklist(blacklist)
            .build()
    );
    assert rs != null;
    System.out.println(routeRepository.findAll());
  }

}
