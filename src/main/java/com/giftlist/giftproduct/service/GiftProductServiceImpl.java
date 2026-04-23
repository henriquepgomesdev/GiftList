package com.giftlist.giftproduct.service;

import com.giftlist.giftlist.model.GiftListDto;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.mapper.GiftProductMapper;
import com.giftlist.giftproduct.model.GiftProductDto;
import com.giftlist.giftproduct.model.GiftProductInput;
import com.giftlist.giftproduct.repository.GiftProductRepository;
import com.giftlist.product.domain.Product;
import com.giftlist.product.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiftProductServiceImpl implements GiftProductService {

    @PersistenceContext
    private EntityManager entityManager;

    private final GiftProductRepository giftProductRepository;

    private final ProductService productService;

    private final GiftProductMapper mapper;

    @Override
    @Transactional
    public GiftProduct createGiftProduct(Long productId, GiftProductInput giftProductInput) {
        Optional<Product> productOpt = productService.findProductById(productId);
        if (productOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        Product product = productOpt.get();

        GiftProduct giftProduct = new GiftProduct();
        giftProduct.setProduct(product);
        giftProduct.setAmount(giftProductInput.amount());
        giftProduct.setLink(giftProductInput.link());

        entityManager.persist(giftProduct);

        return giftProduct;
    }

    @Override
    @Transactional
    public GiftProduct saveGiftProduct(GiftProduct product) {
        return giftProductRepository.save(product);
    }

    @Override
    public Optional<GiftProduct> findGiftProductById(Long id) {
        return giftProductRepository.findById(id);
    }

    @Override
    public Optional<GiftProductDto> findGiftProductDtoById(Long id) {
        return findGiftProductById(id).map(mapper::toDTO);
    }

    @Override
    public List<GiftProductDto> findAllGiftProducts() {
        return giftProductRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteGiftProduct(Long productId) {
        giftProductRepository.deleteById(productId);
    }
}