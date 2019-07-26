package com.example.mongodbtest.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author aaron
 */
@Configuration
@Slf4j
public class MongoConfig {

  @Bean
  @ConfigurationProperties(
      prefix = "spring.data.mongodb.custom")
  MongoSettingsProperties mongoSettingsProperties() {
    return new MongoSettingsProperties();
  }

  @Autowired
  private MongoSettingsProperties mongoSettingsProperties;


  /**
   * 覆盖默认的MongoDbFactory
   *
   * @return mongoDbFactory
   */
  @Bean
  MongoDbFactory mongoDbFactory() {

    //客户端配置（连接数、副本集群验证）
    MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
    builder.connectionsPerHost(mongoSettingsProperties.getConnectionsPerHost());
    builder.minConnectionsPerHost(mongoSettingsProperties.getMinConnectionsPerHost());
    if (mongoSettingsProperties.getReplicaSet() != null) {
      builder.requiredReplicaSetName(mongoSettingsProperties.getReplicaSet());
    }
    // 设置偏好于在Secondary上读
    builder.readPreference(ReadPreference.secondaryPreferred());

    MongoClientOptions mongoClientOptions = builder.build();

    // MongoDB地址列表
    List<ServerAddress> serverAddresses = new ArrayList<>();
    for (String host : mongoSettingsProperties.getHosts()) {
      serverAddresses.add(new ServerAddress(host));
    }
    if (log.isInfoEnabled()) {
      log.info("MongoDB ReplicaSet serverAddresses:{}", serverAddresses.toString());
    }

    // 连接认证
    MongoCredential mongoCredential = null;
    if (mongoSettingsProperties.getUsername() != null) {
      String authDB =
          mongoSettingsProperties.getAuthenticationDatabase() != null ? mongoSettingsProperties
              .getAuthenticationDatabase() : mongoSettingsProperties.getDatabase();

      mongoCredential = MongoCredential.createScramSha1Credential(
          mongoSettingsProperties.getUsername(),
          authDB,
          mongoSettingsProperties.getPassword().toCharArray());
    }
    if (log.isInfoEnabled()) {
      log.info("mongoCredential: {}", mongoCredential);
    }

    //创建客户端和Factory
    MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredential, mongoClientOptions);

    return new SimpleMongoDbFactory(mongoClient, mongoSettingsProperties.getDatabase());
  }

  @Bean
  MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
    return new MongoTemplate(mongoDbFactory);
  }
}
