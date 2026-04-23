package com.giftlist.giftbuyer.service;

import com.giftlist.common.domain.People;
import com.giftlist.common.service.PeopleService;
import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.mapper.GiftBuyerMapper;
import com.giftlist.giftbuyer.model.GiftBuyerDto;
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

    private final GiftBuyerMapper mapper;

    @Override
    @Transactional
    public GiftBuyerDto giftBuy(Long giftProductId, GiftBuyerInput giftProductInput) {
        Optional<GiftProduct> giftProductOpt = giftProductService.findGiftProductById(giftProductId);
        if (giftProductOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        GiftProduct product = giftProductOpt.get();
        return this.saveGiftBuyer(giftProductInput, product);
    }

    @Override
    @Transactional
    public GiftBuyerDto saveGiftBuyer(GiftBuyerInput input, GiftProduct product) {
        Optional<People> peopleOpt = peopleService.findPeopleByCpf(input.person().cpf());
        People people = new People();
        if (peopleOpt.isEmpty()) {
            people.setCpf(input.person().cpf());
            people.setName(input.person().name());
            people.setPhone(input.person().phone());
        } else {
            people = peopleOpt.get();
        }
        GiftBuyer giftBuyer = new GiftBuyer();
        giftBuyer.setPeople(people);
        giftBuyer.setGiftProduct(product);
        return mapper.toDTO(giftBuyerRepository.save(giftBuyer));
    }

    @Override
    public Optional<GiftBuyerDto> findGiftBuyerById(Long id) {
        return giftBuyerRepository.findById(id)
                .map(mapper::toDTO);
    }

    @Override
    public List<GiftBuyerDto> findAllGiftBuyers() {
        return giftBuyerRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteGiftBuyer(Long productId) {
        giftBuyerRepository.deleteById(productId);
    }
}