package com.bithumbsystems.bootstarterproject.domain.menu.service;

import com.bithumbsystems.bootstarterproject.domain.menu.repository.MenuRepository;
import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MenuService {
    @Autowired
    MenuRepository repository;

    public Flux<Menu> menuList() {
        return repository.findAll();
    }
}
