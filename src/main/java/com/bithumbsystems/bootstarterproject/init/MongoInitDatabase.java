package com.bithumbsystems.bootstarterproject.init;

import com.bithumbsystems.bootstarterproject.config.MongoConfig;
import com.bithumbsystems.bootstarterproject.domain.sample.model.Book;
import com.bithumbsystems.bootstarterproject.domain.sample.model.Image;
import com.bithumbsystems.bootstarterproject.domain.sample.model.Memo;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.BookRepository;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.ImageRepository;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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


}
