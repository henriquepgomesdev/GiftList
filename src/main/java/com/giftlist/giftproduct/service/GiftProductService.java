package com.giftlist.giftproduct.service;


import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.model.GiftProductInput;

import java.util.List;
import java.util.Optional;

public interface GiftProductService {

    GiftProduct createGiftProduct(Long productId, GiftProductInput giftProductInput);

    GiftProduct saveGiftProduct(GiftProduct product);

    Optional<GiftProduct> findGiftProductById(Long id);

    List<GiftProduct> findAllGiftProducts();

    void deleteGiftProduct(Long productId);
}