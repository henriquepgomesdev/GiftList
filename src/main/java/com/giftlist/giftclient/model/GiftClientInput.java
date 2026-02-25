package com.giftlist.giftclient.model;

import java.math.BigDecimal;

public record GiftClientInput(
        BigDecimal amount,
        String link
) {
}
