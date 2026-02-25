package com.giftlist.giftclient.service;

import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.model.GiftClientInput;
import com.giftlist.giftclient.repository.GiftClientRepository;
import com.giftlist.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftClientServiceImpl implements GiftClientService {

    private final GiftClientRepository giftProductRepository;

    private final ProductService productService;

    @Override
    @Transactional
    public GiftClient createGiftClient(Long productId, GiftClientInput giftProductInput) {
        GiftClient giftProduct = new GiftClient();
        return saveGiftClient(giftProduct);
    }

    @Override
    @Transactional
    public GiftClient saveGiftClient(GiftClient product) {
        return giftProductRepository.save(product);
    }

    @Override
    public Optional<GiftClient> findGiftClientById(Long id) {
        return giftProductRepository.findById(id);
    }

    @Override
    public List<GiftClient> findAllGiftClients() {
        return giftProductRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteGiftClient(Long productId) {
        giftProductRepository.deleteById(productId);
    }
}