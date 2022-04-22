package com.bithumbsystems.bootstarterproject.domain.coin.repository;

import com.bithumbsystems.bootstarterproject.domain.coin.repository.entity.Coin;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CoinRepository extends ReactiveCrudRepository<Coin, String> {
    Mono<Coin> findBySymbol(String symbol);

    @Query("{'coinNameKr': {$regex: ?0 }}")
    Flux<Coin> findRegexByContext(String test);

    @Query("{'coinNameKr': {$regex: ?0 }}")
    Flux<Coin> findRegexPaging(String test, Pageable pageable);
}
