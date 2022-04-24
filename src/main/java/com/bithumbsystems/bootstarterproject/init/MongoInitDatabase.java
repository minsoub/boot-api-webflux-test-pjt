package com.bithumbsystems.bootstarterproject.init;

import com.bithumbsystems.bootstarterproject.domain.coin.repository.entity.Coin;
import com.bithumbsystems.bootstarterproject.domain.coin.repository.CoinRepository;
import com.bithumbsystems.bootstarterproject.domain.menu.repository.MenuRepository;
import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.Menu;
import com.bithumbsystems.bootstarterproject.domain.menu.repository.entity.MenuItem;
import com.bithumbsystems.bootstarterproject.domain.sample.model.*;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoInitDatabase {

    @Bean
    CommandLineRunner init2(BookRepository operations) {
        return args -> {
            Flux.just(
                    new Book("Test book 01"),
                    new Book("Test book 02"),
                    new Book("Test book 03")
            )
                    .flatMap(operations::save)
                    .subscribe(System.out::println);
        };
    }

    @Bean
    CommandLineRunner memo(MemoRepository operation) {
        return args -> {
            Flux.just(
                    new Memo(1, "memo test 01", (int)(System.currentTimeMillis()), false),
                    new Memo(2, "memo test 02", (int)(System.currentTimeMillis()), false),
                    new Memo(3, "memo test 03", (int)(System.currentTimeMillis()), false),
                    new Memo(4, "memo test 04", (int)(System.currentTimeMillis()), false),
                    new Memo(5, "memo test 05", (int)(System.currentTimeMillis()), true),
                    new Memo(6, "memo test 06", (int)(System.currentTimeMillis()), false),
                    new Memo(7, "memo test 07", (int)(System.currentTimeMillis()), false),
                    new Memo(8, "memo test 08", (int)(System.currentTimeMillis()), false),
                    new Memo(9, "memo test 09", (int)(System.currentTimeMillis()), false),
                    new Memo(10, "memo test 10", (int)(System.currentTimeMillis()), true)
            ).flatMap(operation::save)
                    .subscribe(System.out::println);
        };
    }
    @Bean
    CommandLineRunner coin(CoinRepository operation) {
        List<Coin> list = new ArrayList<>();
        for (int i=0; i<500; i++) {
            list.add(new Coin("BTC"+String.valueOf(i), "BTC1", "비트코인", "http://dev.bithumb.com/resources/img/coin/coin-343434343434343434433433.png", "Y", "Y", "N", "XXX"));
        }
        return args -> {
            Flux.fromIterable(list).flatMap(operation::save).subscribe(System.out::println);
//            Flux.just(list).flatMap(operation::save)
//                    .subscribe(System.out::println);
        };
    }

    @Bean
    CommandLineRunner redisCoin(BitCoinRepository operation) {
        List<BitCoin> list = new ArrayList<>();
        for (int i=0; i<500; i++) {
            list.add(new BitCoin("BTC"+String.valueOf(i), "BTC1", "비트코인", "http://dev.bithumb.com/resources/img/coin/coin-343434343434343434433433.png", "Y", "Y", "N", "XXX"));
        }
        for (BitCoin coin: list) {
            operation.save(coin);
        }
        return args -> {

        };
//        return args -> {
//            Flux.fromIterable(list).flatMap(operation::save).subscribe(System.out::println);
////            Flux.just(list).flatMap(operation::save)
////                    .subscribe(System.out::println);
//        };
    }

    @Bean
    CommandLineRunner init(ImageRepository operations) {
        return args -> {
            System.out.println("called MongiInitDatabase init...");

            if (operations == null) {
                System.out.println("operations repository is null");
            }else {
                System.out.println("operations repository is not null");
            }
            operations.deleteAll();
            //operations.dropCollection(Image.class);
            //operations.insert()
            operations.insert(Image.builder().id("1").name("test01.jpg").build()).subscribe(System.out::println);
            operations.insert(Image.builder().id("2").name("test02.jpg").build()).subscribe(System.out::println);
            operations.insert(Image.builder().id("3").name("test03.jpg").build()).subscribe(System.out::println);


            operations.findAll()
                    .subscribe(System.out::println);
        };
    }

    @Bean
    CommandLineRunner menu(MenuRepository operations) {

        List<Menu> list = new ArrayList<>();
        Menu menu = new Menu();
        menu.setId("group-dashboard");
        menu.setTitle("Navigation");
        menu.setType("group");

        List<MenuItem> items = new ArrayList<>();
        MenuItem item = new MenuItem();
        item.setId("dashboard");
        item.setTitle("Dashboard");
        item.setType("item");
        item.setUrl("/dashboard/default");
        item.setAuth(true);
        item.setIcon("DashboardOutlined");
        item.setBreadcrumbs(false);
        item.setChildren(null);
        items.add(item);

        menu.setChildren(items);
        list.add(menu);

        Menu menu1 = new Menu();
        menu1.setId("authentication");
        menu1.setTitle("Authentication");
        menu1.setType("group");

        List<MenuItem> items2 = new ArrayList<>();
        MenuItem item1 = new MenuItem();
        item1.setId("login1");
        item1.setTitle("Login");
        item1.setType("item");
        item1.setUrl("/login");
        item1.setTarget(true);
        item1.setIcon("LoginOutlined");
        item1.setChildren(null);
        items2.add(item1);

        MenuItem item2 = new MenuItem();
        item2.setId("register1");
        item2.setTitle("'Register'");
        item2.setType("item");
        item2.setUrl("/register");
        item2.setTarget(true);
        item2.setIcon("ProfileOutlined");
        item2.setChildren(null);
        items2.add(item2);


        menu1.setChildren(items2);
        list.add(menu1);

        return args -> {
            Flux.fromIterable(list).flatMap(operations::save).subscribe(System.out::println);
//            Flux.just(list).flatMap(operation::save)
//                    .subscribe(System.out::println);
        };
    }
}
