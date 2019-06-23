package com.example.mongodbtest;

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
class Routes {

  @Id
  private Long id;
  private String routeName;
  private String routePath;
  private List<String> blacklist;
}
