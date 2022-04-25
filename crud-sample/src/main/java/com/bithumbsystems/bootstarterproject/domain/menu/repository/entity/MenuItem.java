package com.bithumbsystems.bootstarterproject.domain.menu.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/*
    {
        id: 'group-dashboard',
        title: 'Navigation',
        type: 'group',
        children: [
            {
                id: 'dashboard',
                title: 'Dashboard',
                type: 'item',
                url: '/dashboard/default',
                auth: true, // 로그인 했을 때만 사용가능 메뉴
                icon: icons.DashboardOutlined,
                breadcrumbs: false
            }
        ]
    },
 */
@Data
@Document
public class MenuItem {
    @Id
    String id;
    String title;
    String type;
    String url;
    boolean auth;
    String icon;
    boolean target;
    boolean breadcrumbs;
    @DBRef(db = "menuitem", lazy = true)
    List<MenuItem> children;
}
