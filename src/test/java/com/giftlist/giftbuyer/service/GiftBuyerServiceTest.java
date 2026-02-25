package com.giftlist.giftbuyer.service;

import com.giftlist.common.domain.People;
import com.giftlist.common.service.PeopleService;
import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftbuyer.model.GiftBuyerInput;
import com.giftlist.giftbuyer.repository.GiftBuyerRepository;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.service.GiftProductService;
import com.giftlist.product.domain.Product;
import jakarta.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftBuyerServiceTest {

    @Mock
    private GiftBuyerRepository giftBuyerRepository;

    @Mock
    private GiftProductService giftProductService;

    @Mock
    private PeopleService peopleService;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private GiftBuyerServiceImpl giftBuyerService;

    private GiftProduct giftProduct;


    @Before
    public void setUp() throws Exception {
        entityManager = mock(EntityManager.class);

        Product product = new Product();
        product.setId(1L);
        product.setName("Produto 1");

        People people = new People();
        people.setCpf("85135289003");
        people.setPhone("62999914474");
        people.setName("Henrique");

        giftProduct = new GiftProduct();
        giftProduct.setId(1L);
        giftProduct.setAmount(new BigDecimal(5));
        giftProduct.setLink("https://s.shopee.com.br/6Kvii5uX4J");
        giftProduct.setProduct(product);

        when(giftProductService.findGiftProductById(1L)).thenReturn(Optional.of(giftProduct));
        when(peopleService.findPeopleByCpf("85135289003")).thenReturn(Optional.of(people));
    }

    @Test
    public void testCreateGiftBuyer() {
        GiftBuyer giftBuyer = new GiftBuyer();
        giftBuyer.setGiftProduct(giftProduct);

        when(giftBuyerRepository.save(any(GiftBuyer.class))).thenReturn(giftBuyer);

        GiftBuyerInput giftBuyerInput = new GiftBuyerInput("Henrique", "85135289003", "62999914474");

        GiftBuyer giftBuyerResult = giftBuyerService.giftBuy(1L, giftBuyerInput);

        assertNotNull("Gift buyer criado não deve ser nulo", giftBuyerResult);

        verify(giftProductService, times(1)).findGiftProductById(1L);
    }

}
