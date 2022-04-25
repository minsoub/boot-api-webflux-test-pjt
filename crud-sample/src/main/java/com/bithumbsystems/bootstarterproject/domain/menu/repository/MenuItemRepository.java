package com.bithumbsystems.bootstarterproject.domain.menu.repository;

import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.MenuItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends ReactiveCrudRepository<MenuItem, String> {
}
