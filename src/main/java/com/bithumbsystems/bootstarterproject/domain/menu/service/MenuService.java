package com.bithumbsystems.bootstarterproject.domain.menu.service;

import com.bithumbsystems.bootstarterproject.domain.menu.repository.MenuItemRepository;
import com.bithumbsystems.bootstarterproject.domain.menu.repository.MenuRepository;
import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MenuService {
    @Autowired
    MenuRepository repository;
    @Autowired
    MenuItemRepository itemRepository;

    public Flux<Menu> menuList() {
        return repository.findAll();
//                .collectList()
//                .flatMap( menu -> itemRepository.findAllById(menu.)
//
//                );
    }
}
