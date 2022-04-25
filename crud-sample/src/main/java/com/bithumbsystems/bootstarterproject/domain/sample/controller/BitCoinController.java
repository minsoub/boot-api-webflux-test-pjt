package com.bithumbsystems.bootstarterproject.domain.sample.controller;

import com.bithumbsystems.bootstarterproject.domain.sample.model.BitCoin;
import com.bithumbsystems.bootstarterproject.domain.sample.service.BitCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BitCoinController {
    @Autowired
    BitCoinService service;

    @GetMapping("/bitcoinlist")
    public List<BitCoin> bitcoinlist() {
        return service.findCoinList();
    }
}
