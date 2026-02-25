package com.giftlist.giftlist.service;

import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.model.GiftListInput;

import java.util.List;
import java.util.Optional;

public interface GiftListService {

    GiftList createGiftList(GiftListInput giftProductInput);

    GiftList saveGiftList(GiftList product);

    Optional<GiftList> findGiftListById(Long id);

    GiftList addGiftProductInGiftList(Long giftProductId, Long giftListId);

    List<GiftList> findAllGiftLists();

    void deleteGiftList(Long productId);
}