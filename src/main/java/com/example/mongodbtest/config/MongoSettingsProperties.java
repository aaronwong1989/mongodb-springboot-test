package com.example.mongodbtest.config;

import java.util.List;
import lombok.Data;

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
