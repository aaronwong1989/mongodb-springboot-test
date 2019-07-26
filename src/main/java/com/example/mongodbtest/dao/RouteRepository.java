package com.example.mongodbtest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author aaron
 */
public interface RouteRepository extends MongoRepository<Routes,Long> {

}
