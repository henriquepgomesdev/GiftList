package com.giftlist.giftproduct.repository;

import com.giftlist.GiftListApplication;
import com.giftlist.config.TestConfig;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.product.domain.Product;
import com.giftlist.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GiftListApplication.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
@Transactional
public class GiftProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GiftProductRepository giftProductRepository;

    @Before
    public void setUp() {
        Product product1 = new Product();
        product1.setName("Produto 1");
        product1.setDescription("Descrição do Produto 1");
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Produto 2");
        product2.setDescription("Descrição do Produto 2");
        productRepository.save(product2);

        GiftProduct giftProduct1 = new GiftProduct();
        giftProduct1.setAmount(new BigDecimal(5));
        giftProduct1.setLink("https://s.shopee.com.br/6Kvii5uX4J");
        giftProduct1.setProduct(product1);

        GiftProduct giftProduct2 = new GiftProduct();
        giftProduct2.setAmount(new BigDecimal(1));
        giftProduct2.setLink("https://s.shopee.com.br/6Kvii5uX4H");
        giftProduct2.setProduct(product2);

        giftProductRepository.save(giftProduct1);

        giftProductRepository.save(giftProduct2);
    }

    @Test
    public void testSearchByName() {
        List<GiftProduct> products = giftProductRepository.findAll();
        int expectedSize = 2;
        String message = String.format("Deveria encontrar %s produto(s) de presente", expectedSize);
        assertEquals(message, expectedSize, products.size());
    }

}
