package com.giftlist.giftbuyer.repository;

import com.giftlist.GiftListApplication;
import com.giftlist.common.domain.People;
import com.giftlist.common.repository.PeopleRepository;
import com.giftlist.config.TestConfig;
import com.giftlist.giftbuyer.domain.GiftBuyer;
import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.repository.GiftListRepository;
import com.giftlist.giftproduct.domain.GiftProduct;
import com.giftlist.giftproduct.repository.GiftProductRepository;
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
public class GiftBuyerRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GiftProductRepository giftProductRepository;

    @Autowired
    private GiftListRepository giftListRepository;

    @Autowired
    private GiftBuyerRepository giftBuyerRepository;

    @Autowired
    private PeopleRepository peopleRepository;

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

        GiftList giftList = new GiftList();
        giftList.setName("Lista de casa nova");

        giftList.addProduct(giftProduct1);
        giftList.addProduct(giftProduct2);

        giftListRepository.save(giftList);

        GiftBuyer giftBuyer = new GiftBuyer();
        giftBuyer.setGiftProduct(giftProduct1);
        People people = new People();
        people.setCpf("85135289003");
        people.setPhone("62999914474");
        people.setName("Maria");
        peopleRepository.save(people);
        giftBuyer.setPeople(people);

        giftBuyerRepository.save(giftBuyer);
    }

    @Test
    public void testSearchByName() {
        String name = "Produto";
        List<GiftBuyer> products = giftBuyerRepository.findAll();
        int expectedSize = 1;
        String message = String.format("Deveria encontrar %s produto(s) de presente com '%s' no nome", expectedSize, name);
        assertEquals(message, expectedSize, products.size());
    }

}
