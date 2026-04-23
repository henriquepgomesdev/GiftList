package com.giftlist.giftclient.service;

import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.model.GiftClientDto;
import com.giftlist.giftclient.model.GiftClientInput;

import java.util.List;
import java.util.Optional;

public interface GiftClientService {

    GiftClient createGiftClient(Long productId, GiftClientInput giftProductInput);

    GiftClient saveGiftClient(GiftClient giftClient);

    Optional<GiftClient> findGiftClientById(Long id);

    List<GiftClientDto> findAllGiftClients();

    void deleteGiftClient(Long giftClientId);
}