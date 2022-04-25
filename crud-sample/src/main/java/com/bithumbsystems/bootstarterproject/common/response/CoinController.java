package com.bithumbsystems.bootstarterproject.common.response;


import com.bithumbsystems.bootstarterproject.domain.coin.model.dto.CoinReqDto;
import com.bithumbsystems.bootstarterproject.domain.coin.repository.entity.Coin;
import com.bithumbsystems.bootstarterproject.domain.coin.service.CoinService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class CoinController {
    @Autowired
    CoinService service;

    /**
     * 전체 코인 리스트 조회
     *
     * @return
     */
    @GetMapping("/coinlist")
    public Flux<Coin> coinlist() {
        return service.findCoinList();
    }

    /**
     * 심볼을 통해서 코임을 검색한다.
     *
     * @param symbol
     * @return
     */
    @GetMapping(value = "/coin/{symbol}")  // , produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseBody
    public Mono<ResponseEntity<?>> getsymbol (@PathVariable String symbol) {
        Gson gson = new Gson();

        return service.findBySymbol(symbol)
//                .map(coin -> ResponseEntity.status(HttpStatus.OK)
//                                        .body(GeneralResponse.builder().data(gson.toJson(coin)).status(0)))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                                        .body(GeneralResponse.builder().data("Error").status(-1)));

                .map(resource -> {
                    System.out.println(resource);
                   try {
                       GeneralResponse<?> res = GeneralResponse.builder().data(resource).message("OK").status(0).build();
                       return new ResponseEntity<GeneralResponse<?>>(
                               res,
                               HttpStatus.OK
                       );
                   }catch(Exception ex) {
                       ex.printStackTrace();
                       GeneralResponse<?> res = GeneralResponse.builder().data(ex).message(ex.toString()).status(-1).build();
                       return new ResponseEntity<GeneralResponse<?>>(
                               res,
                               HttpStatus.BAD_REQUEST
                       );
                   }
                });
    }

    /**
     * 심볼을 통해서 코임을 검색한다.
     *
     * @param symbol
     * @return
     */
    @GetMapping(value = "/test/{symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<ResponseEntity> test (@PathVariable String symbol) {
        Gson gson = new Gson();

        return service.findBySymbol(symbol)
//                .map(coin -> ResponseEntity.status(HttpStatus.OK)
//                                        .body(GeneralResponse.builder().data(gson.toJson(coin)).status(0)))
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                                        .body(GeneralResponse.builder().data("Error").status(-1)));

                .map(resource -> {
                    System.out.println(resource);
                    try {
                        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                                .body(resource);
                    }catch(Exception ex) {
                        ex.printStackTrace();
                        return ResponseEntity.badRequest()
                                .body(ex.toString());
                    }
                });
    }

    /**
     * Regex를 통한 검색 수행
     *
     * @param param
     * @return
     */
    @GetMapping(value="/regex-coin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GeneralResponse>> regexContext(@RequestParam String param) {

        return service.findRegexByContext(param)
                .collectList()
                .map(
                        lists -> {
                            GeneralResponse<?> res = GeneralResponse.builder()
                                    .data(lists)
                                    .message("OK")
                                    .status(0).build();

                            return ResponseEntity.ok().body(res);
                        }
                );
    }

    /**
     * Paging을 통한 데이터 조회
     *
     * @param param
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value="/paging-coin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> paging(@RequestParam String param, @RequestParam String start, @RequestParam String end) {
        Pageable page = PageRequest.of(Integer.parseInt(start), Integer.parseInt(end));

        return service.findRegexPaging(param, page)
                .collectList()
                .map(
                        lists -> {
                            GeneralResponse<?> res = GeneralResponse.builder()
                                    .data(lists)
                                    .message("OK")
                                    .status(0).build();

                            return ResponseEntity.ok().body(res);
                        }
                );
//                .onErrorResume(
//                        error -> {
//                            GeneralResponse<?> res = GeneralResponse.builder()
//                                    .data(null)
//                                    .message(error.getMessage())
//                                    .status(-1).build();
//
//                            Mono.just(new ResponseEntity<GeneralResponse<?>>(
//                                    res,
//                                    HttpStatus.BAD_REQUEST
//                            ));

//                            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                                    .body(GeneralResponse.builder()
//                                            .data(error)
//                                            .status(-1)
//                                            .message(error.getMessage())
//                                    )
//                            );
//                        }
//                );
    }


    /**
     * 코인 정보를 등록한다.
     *
     * @param request
     * @return
     */
    @PostMapping("/coin")
    public Mono<ResponseEntity<?>> doInsert(@RequestBody @Valid CoinReqDto request) {
        // validation check

        return service.save(request)
                .map(resource -> {
                    System.out.println(resource);
                    try {
                        GeneralResponse<?> res = GeneralResponse.builder().data(resource).message("OK").status(0).build();
                        return new ResponseEntity<GeneralResponse<?>>(
                                res,
                                HttpStatus.OK
                        );
                    }catch(Exception ex) {
                        ex.printStackTrace();
                        GeneralResponse<?> res = GeneralResponse.builder().data(ex).message(ex.toString()).status(-1).build();
                        return new ResponseEntity<GeneralResponse<?>>(
                                res,
                                HttpStatus.BAD_REQUEST
                        );
                    }
                });

    }

    /**
     * 코인 정보를 수정한다.
     *
     * @param request
     * @return
     */
    @PutMapping("/coin")
    public Mono<ResponseEntity<?>> doUpdate(@RequestBody @Valid CoinReqDto request) {
        // validation check

        return service.save(request)
                .map(resource -> {
                    System.out.println(resource);
                    try {
                        GeneralResponse<?> res = GeneralResponse.builder().data(resource).message("OK").status(0).build();
                        return new ResponseEntity<GeneralResponse<?>>(
                                res,
                                HttpStatus.OK
                        );
                    }catch(Exception ex) {
                        ex.printStackTrace();
                        GeneralResponse<?> res = GeneralResponse.builder().data(ex).message(ex.toString()).status(-1).build();
                        return new ResponseEntity<GeneralResponse<?>>(
                                res,
                                HttpStatus.BAD_REQUEST
                        );
                    }
                });

    }

    @DeleteMapping("/coin/{symbol}")
    public Mono<ResponseEntity<?>> doDelete(@PathVariable String symbol) {
        // validation check

        return service.delete(symbol)
                .map(resource -> {
                    System.out.println(resource);
                    try {
                        GeneralResponse<?> res = GeneralResponse.builder().data(resource).message("OK").status(0).build();
                        return new ResponseEntity<GeneralResponse<?>>(
                                res,
                                HttpStatus.OK
                        );
                    }catch(Exception ex) {
                        ex.printStackTrace();
                        GeneralResponse<?> res = GeneralResponse.builder().data(ex).message(ex.toString()).status(-1).build();
                        return new ResponseEntity<GeneralResponse<?>>(
                                res,
                                HttpStatus.BAD_REQUEST
                        );
                    }
                });

    }

}
