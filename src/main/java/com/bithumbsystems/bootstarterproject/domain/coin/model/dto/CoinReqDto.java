package com.bithumbsystems.bootstarterproject.domain.coin.model.dto;

import com.bithumbsystems.bootstarterproject.domain.coin.repository.entity.Coin;
import lombok.*;

/**
 * 코인 Request DTO class
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
public class CoinReqDto {

    /**
     * key
     */
    private String symbol;

    /**
     * 코인 영문명
     */
    private String coinNameEn;

    /**
     * 코인 한글명
     */
    private String coinNameKr;

    /**
     * 코인 아이콘 URL
     */
    private String iconUrl;

    /**
     * etc1
     */
    private String depositable;

    /**
     * etc2
     */
    private String withdrawable;

    /**
     * etc3
     */
    private String tradable;

    /**
     * etc4
     */
    private String caution;

    @Builder
    public CoinReqDto(String symbol, String coinNameEn, String coinNameKr, String iconUrl, String depositable, String withdrawable, String tradable, String caution) {
        this.symbol = symbol;
        this.coinNameEn = coinNameEn;
        this.coinNameKr = coinNameKr;
        this.iconUrl = iconUrl;
        this.depositable = depositable;
        this.withdrawable = withdrawable;
        this.tradable = tradable;
        this.caution = caution;
    }

    /**
     * DTO로 객체 생성
     *
     * @return
     */
    public Coin toEntity() {
        return Coin.builder()
                .symbol(this.symbol)
                .coinNameEn(this.coinNameEn)
                .coinNameKr(this.coinNameKr)
                .iconUrl(this.iconUrl)
                .depositable(this.depositable)
                .withdrawable(this.withdrawable)
                .tradable(this.tradable)
                .caution(this.caution)
                .build();
    }
}

