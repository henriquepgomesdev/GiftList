package com.giftlist.giftbuyer.service;

import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.model.GiftBuyerInput;

import java.util.List;
import java.util.Optional;

public interface GiftBuyerService {

    GiftBuyer giftBuy(Long giftProductId, GiftBuyerInput giftProductInput);

    GiftBuyer saveGiftBuyer(GiftBuyer product);

    Optional<GiftBuyer> findGiftBuyerById(Long id);

    List<GiftBuyer> findAllGiftBuyers();

    void deleteGiftBuyer(Long productId);
}