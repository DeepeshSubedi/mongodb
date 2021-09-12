package com.deepesh.mongodb.repos;

import com.deepesh.mongodb.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ExpenseRepos extends MongoRepository<Expense,String > {
    @Query("{'name': ?0}")
    Optional<Expense> findByName(String name);

}
