package com.ntou.cs.monkeyblood.repo;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntou.cs.monkeyblood.entity.*;

@Repository
public interface MonkeyBloodRepo extends MongoRepository<BloodPressure, String> {
    
    List<BloodPressure> findByGithubID(String githubID);  

    @Query("{$and: [ {'githubID': ?0 }, {'date': ?1 }]}")
    Stream<BloodPressure> findByGithubIDDate(String githubID, String date);
}