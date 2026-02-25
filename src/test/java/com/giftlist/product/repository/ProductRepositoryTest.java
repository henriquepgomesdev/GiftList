package com.giftlist.product.repository;

import com.giftlist.GiftListApplication;
import com.giftlist.config.TestConfig;
import com.giftlist.product.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GiftListApplication.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

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
    }

    @Test
    public void testSearchByName() {
        String name = "Produto";
        List<Product> products = productRepository.findAllByNameContaining(name);
        int expectedSize = 2;
        String message = String.format("Deveria encontrar %s produto(s) com '%s' no nome", expectedSize, name);
        assertEquals(message, expectedSize, products.size());
    }

}
