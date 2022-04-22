package com.bithumbsystems.bootstarterproject.domain.coin.repository.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Coin implements Serializable {
    @Id
    private String symbol;
    private String coinNameEn;
    private String coinNameKr;
    private String iconUrl;
    private String depositable;
    private String withdrawable;
    private String tradable;
    private String caution;

    @Builder
    public Coin(String symbol, String coinNameEn, String coinNameKr, String iconUrl, String depositable, String withdrawable, String tradable, String caution) {
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
