package com.giftlist.giftList.service;

import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.repository.GiftListRepository;
import com.giftlist.giftlist.service.GiftListServiceImpl;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.product.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftListServiceTest {

    @Mock
    private GiftListRepository giftListRepository;

    @InjectMocks
    private GiftListServiceImpl giftListService;

    private GiftList giftList;

    @Before
    public void setUp() {
        Product product1 = new Product();
        product1.setName("Produto 1");
        product1.setDescription("Descrição do Produto 1");

        Product product2 = new Product();
        product2.setName("Produto 2");
        product2.setDescription("Descrição do Produto 2");

        GiftProduct giftProduct1 = new GiftProduct();
        giftProduct1.setAmount(new BigDecimal(5));
        giftProduct1.setLink("https://s.shopee.com.br/6Kvii5uX4J");
        giftProduct1.setProduct(product1);

        GiftProduct giftProduct2 = new GiftProduct();
        giftProduct2.setAmount(new BigDecimal(1));
        giftProduct2.setLink("https://s.shopee.com.br/6Kvii5uX4H");
        giftProduct2.setProduct(product2);

        giftList = new GiftList();
        giftList.setId(1L);
        giftList.setName("Lista de casa nova");

        giftList.addProduct(giftProduct1);
        giftList.addProduct(giftProduct2);
    }

    @Test
    public void testSaveProduct() {
        when(giftListRepository.save(any(GiftList.class))).thenReturn(giftList);

        GiftList giftListResult = giftListService.saveGiftList(giftList);

        assertNotNull("Produto salvo não deveria ser nulo", giftListResult);
        assertEquals("Produto salvo deveria ter o mesmo ID", giftList.getId(), giftListResult.getId());

        verify(giftListRepository, times(1)).save(giftList);
    }

    @Test
    public void testFindProductById() {
        when(giftListRepository.findById(1L)).thenReturn(Optional.of(giftList));

        Optional<GiftList> foundProduct = giftListService.findGiftListById(1L);

        assertTrue("Produto deveria ser encontrado", foundProduct.isPresent());
        assertEquals("Produto encontrado deveria ter o ID correto", 1L, foundProduct.get().getId().longValue());

        verify(giftListRepository, times(1)).findById(1L);
    }
}
