package com.bithumbsystems.bootstarterproject.domain.sample.repository;

import com.bithumbsystems.bootstarterproject.domain.sample.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends ReactiveCrudRepository<Book, String> {
}
