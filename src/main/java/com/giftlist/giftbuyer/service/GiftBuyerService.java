package com.giftlist.giftbuyer.service;

import com.giftlist.giftbuyer.model.GiftBuyerDto;
import com.giftlist.giftbuyer.model.GiftBuyerInput;
import com.giftlist.giftproduct.domain.GiftProduct;

import java.util.List;
import java.util.Optional;

public interface GiftBuyerService {

    GiftBuyerDto giftBuy(Long giftProductId, GiftBuyerInput giftProductInput);

    GiftBuyerDto saveGiftBuyer(GiftBuyerInput input, GiftProduct product);

    Optional<GiftBuyerDto> findGiftBuyerById(Long id);

    List<GiftBuyerDto> findAllGiftBuyers();

    void deleteGiftBuyer(Long productId);
}