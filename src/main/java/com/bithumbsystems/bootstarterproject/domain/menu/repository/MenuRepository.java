package com.bithumbsystems.bootstarterproject.domain.menu.repository;

import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.Menu;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends ReactiveCrudRepository<Menu, String> {
}
