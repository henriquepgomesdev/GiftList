package com.giftlist.giftproduct.model;

import java.math.BigDecimal;

public record GiftProductDto(
        BigDecimal amount,
        String link
) {
}
