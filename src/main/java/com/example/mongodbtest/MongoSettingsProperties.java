package com.example.mongodbtest;

import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author aaron
 */
@Data
public class MongoSettingsProperties {

  private String database;
  private List<String> hosts;
  private String replicaSet;
  private String username;
  private String password;
  private String authenticationDatabase;
  private Integer minConnectionsPerHost = 10;
  private Integer connectionsPerHost = 2;
}
