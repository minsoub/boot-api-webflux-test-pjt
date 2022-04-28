package com.bithumbsystems.bootstarterproject.domain.file.controller;

import com.bithumbsystems.bootstarterproject.common.response.GeneralResponse;
import com.bithumbsystems.bootstarterproject.common.util.AwsConfig;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Log4j2
@RestController
@RequestMapping("/upload")
public class FileController {

    @Autowired
    private AwsConfig config;
    private final Path path = Paths.get("/upload-dir/");

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private S3AsyncClient s3Client;  //   S3AsyncClient.builder().region(Region.AP_NORTHEAST_2).build();

    // debug
//    {
//        try {
//            s3Client = config.getS3Client();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @PostMapping("/file")
    public Mono<ResponseEntity> upload(@RequestPart("onefile")Mono<FilePart> filePartMono) {
        return filePartMono.doOnNext(fp -> System.out.println("Received file : " + fp.filename()))
                .flatMap(fp ->fp.transferTo(path.resolve(fp.filename())))
                .then()
                .map((response) -> {
                    return ResponseEntity.ok("Completed");
                });
    }

    @PostMapping("/multifile")
    public Mono<ResponseEntity> multiupload(@RequestPart("files")Flux<FilePart> partFlux) {
        return partFlux.doOnNext(fp -> System.out.println(fp.filename()))
                .flatMap(fp -> fp.transferTo(path.resolve(fp.filename())))
                .then()
                .map((response) -> {
                    return ResponseEntity.ok("Completed");
                });
    }

    @PostMapping("/s3file")
    public Mono<ResponseEntity> s3upload(@RequestHeader HttpHeaders headers, @RequestPart("onefile") Mono<FilePart> filePartMono) {  // @RequestBody Flux<ByteBuffer> body) {  // @RequestPart("onefile")Mono<FilePart> filePartMono) {

        try {
            s3Client = config.getS3Client();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileKey = UUID.randomUUID().toString();
        MediaType mediaType = headers.getContentType();

        if (mediaType == null) mediaType = MediaType.APPLICATION_OCTET_STREAM;

        //Flux<DataBuffer> buffer = filePartMono.block().content();



//        Flux<ByteBuffer> buffer = filePartMono
//                .flatMap(fp->fp.content().flatMap(
//                                dataBuffer -> {
//                                    return Mono.from(dataBuffer.asByteBuffer());  // , ByteBuffer.class);
//                                }
//                        )
//                );
//
//        CompletableFuture future = s3Client
//                .putObject(
//                        PutObjectRequest.builder()
//                                .bucket(bucketName)
//                                .key(fileKey.toString())
//                                .contentType(mediaType.toString())
//                                .build(),
//                        AsyncRequestBody.fromByteBuffer(  //.fromPublisher(
//                                 filePartMono
//                                        .flatMap(fp->fp.content().flatMap(
//                                                dataBuffer -> {
//                                                    return dataBuffer.asByteBuffer();
//                                                }
//                                             )
//                                       )
//                        )
//                );  // filePartMono  content())  // ByteBuffer
//
//
//        return Mono.fromFuture(future)
//                .map((response) -> {
//                    return ResponseEntity.ok();
//                });
//
//
        return filePartMono.doOnNext(fp -> System.out.println("Received file : " + fp.filename()))
                .flatMap(fp -> {
                    fp.content().flatMap(
                            pDataBuffer -> {
                                byte[] bytes = new byte[pDataBuffer.readableByteCount()];
                                pDataBuffer.read(bytes);

                                s3Client.putObject(
                                        PutObjectRequest.builder().
                                                bucket(bucketName).
                                                key(fp.filename()).
                                                build(),
                                        AsyncRequestBody.fromBytes(bytes) //  .fromByteBuffer(pDataBuffer.asByteBuffer())
                                ).whenComplete((resp, err) -> s3Client.close())
                                        .join();

                                ResponseEntity s = ResponseEntity.ok(GeneralResponse.builder().build());

                                return Mono.just(s); // ResponseEntity.ok());  // .body(GeneralResponse.builder().data(null).message("ok").status(0)));
                            }
                        );
                    ResponseEntity s = ResponseEntity.ok(GeneralResponse.builder().build());
                    return Mono.just(s);
                });
    }
}
