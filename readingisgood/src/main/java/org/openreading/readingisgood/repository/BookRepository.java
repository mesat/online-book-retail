package org.openreading.readingisgood.repository;

import org.openreading.readingisgood.model.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book,String> {
}
