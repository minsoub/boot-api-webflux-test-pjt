package com.bithumbsystems.bootstarterproject.domain.sample.service;

import com.bithumbsystems.bootstarterproject.domain.sample.model.BookImage;
import com.bithumbsystems.bootstarterproject.domain.sample.model.Image;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {
    private static String UPLOAD_ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    public ImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Flux<BookImage> findAllImages() {
        try {
            return Flux.fromIterable(
                    Files.newDirectoryStream(Paths.get(UPLOAD_ROOT)))
                    .map(path->
                            new BookImage(path.hashCode(), path.getFileName().toString()));
        }catch(IOException e) {
            return Flux.empty();
        }
    }

    public Mono<Resource> findOneImage(String filename) {
        return Mono.fromSupplier(()->
                resourceLoader.getResource(
                        "file:" + UPLOAD_ROOT + "/"+filename
                ));
    }

    public Mono<Void> createImage(Flux<FilePart> files) {
        return files.flatMap(file -> file.transferTo(
                Paths.get(UPLOAD_ROOT, file.filename()).toFile())).then();
    }

    public Mono<Void> deleteImage(String filename) {
        return Mono.fromRunnable(() -> {
            try {
                Files.deleteIfExists(Paths.get(UPLOAD_ROOT, filename));
            }catch(IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Bean
    CommandLineRunner setUp() throws IOException {
        return args -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));

            Files.createDirectories(Paths.get(UPLOAD_ROOT));

            FileCopyUtils.copy("Test file1", new FileWriter(UPLOAD_ROOT + "/test-01.jpg"));
            FileCopyUtils.copy("Test file2", new FileWriter(UPLOAD_ROOT + "/test-02.jpg"));
            FileCopyUtils.copy("Test file3", new FileWriter(UPLOAD_ROOT + "/test-03.jpg"));
        };
    }
}
