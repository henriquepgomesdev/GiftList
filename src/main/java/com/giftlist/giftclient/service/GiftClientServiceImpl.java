package com.giftlist.giftclient.service;

import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.mapper.GiftClientMapper;
import com.giftlist.giftclient.model.GiftClientDto;
import com.giftlist.giftclient.model.GiftClientInput;
import com.giftlist.giftclient.repository.GiftClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftClientServiceImpl implements GiftClientService {

    private final GiftClientRepository giftClientRepository;

    private final GiftClientMapper mapper;

    @Override
    @Transactional
    public GiftClient createGiftClient(Long productId, GiftClientInput giftProductInput) {
        GiftClient giftProduct = new GiftClient();
        return saveGiftClient(giftProduct);
    }

    @Override
    @Transactional
    public GiftClient saveGiftClient(GiftClient product) {
        return giftClientRepository.save(product);
    }

    @Override
    public Optional<GiftClient> findGiftClientById(Long id) {
        return giftClientRepository.findById(id);
    }

    @Override
    public List<GiftClientDto> findAllGiftClients() {
        return giftClientRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteGiftClient(Long productId) {
        giftClientRepository.deleteById(productId);
    }
}