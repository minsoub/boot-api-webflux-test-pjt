package com.bithumbsystems.bootstarterproject.domain.coin.service;

import com.bithumbsystems.bootstarterproject.domain.coin.model.dto.CoinReqDto;
import com.bithumbsystems.bootstarterproject.domain.coin.repository.entity.Coin;
import com.bithumbsystems.bootstarterproject.domain.coin.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoinService {
    @Autowired
    CoinRepository repository;

    /**
     * 코인 리스트 조회
     *
     * @return
     */
    public Flux<Coin> findCoinList() { return repository.findAll(); }

    /**
     * Regex를 사용한 쿼리 수행
     *
     * @param text
     * @return
     */
    public Flux<Coin> findRegexByContext(String text) {
        return repository.findRegexByContext(text);
    }

    /**
     * 명칭으로 조회
     *
     * @param symbol
     * @return
     */
    public Mono<Coin> findBySymbol(String symbol) {
        return repository.findBySymbol(symbol);
    }

    /**
     * Paging이 가능한 조회
     *
     * @param text
     * @param pageable
     * @return
     */
    public Flux<Coin> findRegexPaging(String text, Pageable pageable) {
        return repository.findRegexPaging(text, pageable);
    }

    /**
     * Coin 정보를 저장한다.
     *
     * @param dto
     * @return
     */
    public Mono<Coin> save(CoinReqDto dto) {
        return repository.save(dto.toEntity());
    }

    /**
     * Coin 정보를 삭제한다.
     * @param symbol
     * @return
     */
    public Mono<Void> delete(String symbol) {
        Mono<Coin> coin = repository.findBySymbol(symbol);

        return repository.delete(coin.block());
    }
}
