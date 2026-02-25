package com.giftlist.giftbuyer.model;

import com.giftlist.common.model.PeopleInput;

public record GiftBuyerInput(
        PeopleInput person
) {
    public GiftBuyerInput(String name, String cpf, String phone) {
        this(new PeopleInput(name, cpf, phone));
    }
}
