package com.giftlist.giftclient.model;

import java.math.BigDecimal;

public record GiftClientDto(
        BigDecimal amount,
        String link
) {
}
