package com.example.mongodbtest;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author aaron
 */
public interface RouteRepository extends MongoRepository<Routes,Long> {

}
