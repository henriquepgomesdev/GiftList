package com.giftlist.giftbuyer.service;

import com.giftlist.common.domain.People;
import com.giftlist.common.service.PeopleService;
import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.model.GiftBuyerInput;
import com.giftlist.giftbuyer.repository.GiftBuyerRepository;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.service.GiftProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftBuyerServiceImpl implements GiftBuyerService {

    private final GiftBuyerRepository giftBuyerRepository;

    private final GiftProductService giftProductService;

    private final PeopleService peopleService;

    @Override
    @Transactional
    public GiftBuyer giftBuy(Long giftProductId, GiftBuyerInput giftProductInput) {
        Optional<GiftProduct> giftProductOpt = giftProductService.findGiftProductById(giftProductId);
        if (giftProductOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        GiftProduct product = giftProductOpt.get();

        Optional<People> peopleOpt = peopleService.findPeopleByCpf(giftProductInput.person().cpf());
        People people = new People();
        if (peopleOpt.isEmpty()) {
            people.setCpf(giftProductInput.person().cpf());
            people.setName(giftProductInput.person().name());
            people.setPhone(giftProductInput.person().phone());
        } else {
            people = peopleOpt.get();
        }

        GiftBuyer giftBuyer = new GiftBuyer();
        giftBuyer.setPeople(people);

        giftBuyer.setGiftProduct(product);

        return this.saveGiftBuyer(giftBuyer);
    }

    @Override
    @Transactional
    public GiftBuyer saveGiftBuyer(GiftBuyer product) {
        return giftBuyerRepository.save(product);
    }

    @Override
    public Optional<GiftBuyer> findGiftBuyerById(Long id) {
        return giftBuyerRepository.findById(id);
    }

    @Override
    public List<GiftBuyer> findAllGiftBuyers() {
        return giftBuyerRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteGiftBuyer(Long productId) {
        giftBuyerRepository.deleteById(productId);
    }
}