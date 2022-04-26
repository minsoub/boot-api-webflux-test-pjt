package com.bithumbsystems.bootstarterproject.domain.sample.controller;

import com.bithumbsystems.bootstarterproject.domain.sample.model.Book;
import com.bithumbsystems.bootstarterproject.domain.sample.repository.BookRepository;
import com.bithumbsystems.bootstarterproject.domain.sample.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
public class BookController {

    private final BookRepository repository;
    private static final String BASE_PATH = "/images";
    private static final String FILENAME = "{filename:.+}";
    @Autowired
    private ImageService imageService;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public Flux<Book> list() {
        return repository.findAll();
    }

    @GetMapping(value = BASE_PATH+"/"+FILENAME+"/raw", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Mono<ResponseEntity<?>> oneRawImage (@PathVariable String filename) {
        return imageService.findOneImage(filename)
                .map(resource -> {
                    try {
                        return ResponseEntity.ok()
                                .contentLength(resource.contentLength())
                                .body(new InputStreamResource(
                                        resource.getInputStream()
                                ));
                    }catch(IOException e) {
                        return ResponseEntity.badRequest()
                                .body("Couldn't find " + filename + " => " + e.getMessage());
                    }
                });
    }

}
