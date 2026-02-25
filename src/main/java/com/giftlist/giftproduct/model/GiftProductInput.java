package com.giftlist.giftproduct.model;

import java.math.BigDecimal;

public record GiftProductInput(
        BigDecimal amount,
        String link
) {
}
