package com.github.martinhermes86.asterixapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterRepo extends MongoRepository<Character, String> {
    @Query("{ '$or': [ { 'name': ?0 }, {'age': ?1}, { 'occupation': ?2 } ] }")
    List<Character> findByNameOrAgeOrOccupation(@Param("name") String name,
                                                @Param("age") Integer age,
                                                @Param("occupation") String occupation);
}
