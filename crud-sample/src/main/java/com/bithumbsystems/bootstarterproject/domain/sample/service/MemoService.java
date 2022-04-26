package com.bithumbsystems.bootstarterproject.domain.sample.service;

import com.bithumbsystems.bootstarterproject.domain.sample.model.Memo;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MemoService {
    @Autowired
    private MemoRepository repository;

    public Flux<Memo> findMemoList() {
        return repository.findAll();
    }
}
