package com.giftlist.giftproduct.service;


import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.model.GiftProductInput;
import com.giftlist.giftproduct.repository.GiftProductRepository;
import com.giftlist.product.domain.Product;
import com.giftlist.product.repository.ProductRepository;
import com.giftlist.product.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftProductServiceTest {

    @Mock
    private GiftProductRepository giftProductRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<GiftProduct> typedQuery;

    @InjectMocks
    private GiftProductServiceImpl giftProductService;

    private Product product;

    private GiftProduct giftProduct;


    @Before
    public void setUp() throws Exception {
        entityManager = mock(EntityManager.class);

        product = new Product();
        product.setId(1L);
        product.setName("Produto 1");

        giftProduct = new GiftProduct();
        giftProduct.setId(1L);
        giftProduct.setAmount(new BigDecimal(5));
        giftProduct.setLink("https://s.shopee.com.br/6Kvii5uX4J");
        giftProduct.setProduct(product);

        when(productService.findProductById(1L)).thenReturn(Optional.of(product));

        Field entityManagerField = GiftProductServiceImpl.class.getDeclaredField("entityManager");
        entityManagerField.setAccessible(true);
        entityManagerField.set(giftProductService, entityManager);
    }

    @Test
    public void testCreateGift() {
        when(productService.findProductById(1L)).thenReturn(Optional.of(product));

        GiftProductInput giftProductInput = new GiftProductInput(new BigDecimal(5), "https://s.shopee.com.br/6Kvii5uX4J");

        GiftProduct giftProduct = giftProductService.createGiftProduct(1L, giftProductInput);

        assertNotNull("Pedido criado não deveria ser nulo", giftProduct);

        verify(productService, times(1)).findProductById(1L);

        verify(entityManager, atLeastOnce()).persist(any(GiftProduct.class));
    }
}
