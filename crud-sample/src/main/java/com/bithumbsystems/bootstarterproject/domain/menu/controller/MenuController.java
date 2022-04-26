package com.bithumbsystems.bootstarterproject.domain.menu.controller;

import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.Menu;
import com.bithumbsystems.bootstarterproject.domain.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MenuController {
    @Autowired
    private MenuService service;


    @GetMapping("/menulist")
    public Flux<Menu> menulist() {
        // DBRef 는 이전에는 mongodb reactive에서 지원하지 않는다.  현재 시점에서 지원되는지 또는 지원되지 않는다면 다른 방안에 대해서 검토가 필요.
        // https://stackoverflow.com/questions/50058861/how-to-use-db-references-with-reactive-spring-data-mongodb
        // 위의 URL에서 reactive 지원은 안되지만 하는 방법에 대해서 설명 되어지고 있다.
        return service.menuList();
    }
}
