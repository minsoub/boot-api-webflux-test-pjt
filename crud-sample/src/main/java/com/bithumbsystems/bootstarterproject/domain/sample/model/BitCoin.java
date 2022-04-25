package com.bithumbsystems.bootstarterproject.domain.sample.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("coin-data")
public class BitCoin implements Serializable {
    @Id
    String symbol;
    String coinNameEn;
    String coinNameKr;
    String iconUrl;
    String depositable;
    String withdrawable;
    String tradable;
    String caution;

    public BitCoin(String symbol, String coinNameEn, String coinNameKr, String iconUrl, String depositable, String withdrawable, String tradable, String caution) {
        this.symbol = symbol;
        this.coinNameEn = coinNameEn;
        this.coinNameKr = coinNameKr;
        this.iconUrl = iconUrl;
        this.depositable = depositable;
        this.withdrawable = withdrawable;
        this.tradable = tradable;
        this.caution = caution;
    }
}
