package com.bithumbsystems.bootstarterproject.domain.sample.repository;

import com.bithumbsystems.bootstarterproject.domain.sample.model.Image;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ImageRepository extends ReactiveMongoRepository<Image, String> {  // ReactiveCrudRepository<Image, String> {
    Mono<Image> findByName(String name);
}
