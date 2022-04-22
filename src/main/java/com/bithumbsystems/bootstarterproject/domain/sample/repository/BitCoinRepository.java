package com.bithumbsystems.bootstarterproject.domain.sample.repository;

import com.bithumbsystems.bootstarterproject.domain.sample.model.BitCoin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitCoinRepository  extends CrudRepository<BitCoin, String> { // ReactiveCrudRepository<BitCoin, String> {
    List<BitCoin> findAll();
}
