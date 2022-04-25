package com.bithumbsystems.bootstarterproject.domain.sample.repository;

import com.bithumbsystems.bootstarterproject.domain.sample.model.Memo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemoRepository extends ReactiveCrudRepository<Memo, Integer> {
}
