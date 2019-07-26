package com.example.mongodbtest.dao;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author aaron
 */
@Data
@Builder
@Document(collection = "myRoutes")
public class Routes {

  @Id
  private Long id;
  private String routeName;
  private String routePath;
  private String filter;
  private List<String> blacklist;
}
