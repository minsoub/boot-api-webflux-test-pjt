package com.bithumbsystems.bootstarterproject.domain.sample.service;

import com.bithumbsystems.bootstarterproject.domain.sample.model.BitCoin;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.BitCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitCoinService {
    @Autowired
    BitCoinRepository repository;

    public List<BitCoin> findCoinList() {
        return repository.findAll();
    }
}
