package com.giftlist.giftbuyer.model;

import com.giftlist.common.model.PeopleInput;

public record GiftBuyerDto(
        PeopleInput person
) {
    public GiftBuyerDto(String name, String cpf, String phone) {
        this(new PeopleInput(name, cpf, phone));
    }
}
