package com.bithumbsystems.bootstarterproject.domain.sample.controller;

import com.bithumbsystems.bootstarterproject.domain.sample.model.Memo;
import com.bithumbsystems.bootstarterproject.domain.sample.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MemoController {
    @Autowired
    private MemoService service;

    @GetMapping("/memos")
    public Flux<Memo> books() {
        return service.findMemoList();
    }
}
