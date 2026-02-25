package com.giftlist.giftlist.service;

import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.model.GiftListInput;
import com.giftlist.giftlist.repository.GiftListRepository;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.service.GiftProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GiftListServiceImpl implements GiftListService {


    private final GiftListRepository giftListRepository;

    private final GiftProductService giftProductService;

    @Override
    @Transactional
    public GiftList createGiftList(GiftListInput giftProductInput) {
        GiftList giftProduct = new GiftList();
        giftProduct.setName(giftProductInput.name());
        return this.saveGiftList(giftProduct);
    }

    @Override
    @Transactional
    public GiftList addGiftProductInGiftList(Long giftProductId, Long giftListId) {
        Optional<GiftProduct> giftProductOpt = giftProductService.findGiftProductById(giftProductId);
        if (giftProductOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        GiftProduct giftProduct = giftProductOpt.get();

        Optional<GiftList> giftListOpt = this.findGiftListById(giftListId);
        if (giftListOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        GiftList giftList = giftListOpt.get();

        giftList.addProduct(giftProduct);

        return this.saveGiftList(giftList);
    }

    @Override
    @Transactional
    public GiftList saveGiftList(GiftList product) {
        return giftListRepository.save(product);
    }

    @Override
    public Optional<GiftList> findGiftListById(Long id) {
        return giftListRepository.findById(id);
    }

    @Override
    public List<GiftList> findAllGiftLists() {
        return giftListRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteGiftList(Long productId) {
        giftListRepository.deleteById(productId);
    }
}