package com.giftlist.product.service;

import com.giftlist.product.domain.Product;
import com.giftlist.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;

    @Before
    public void setUp() {
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Product description 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Product description 2");

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Product 3");
        product3.setDescription("Product description 3");
    }

    @Test
    public void testSaveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product1);

        Product savedProduct = productService.saveProduct(product1);

        assertNotNull("Produto salvo não deveria ser nulo", savedProduct);
        assertEquals("Produto salvo deveria ter o mesmo ID", product1.getId(), savedProduct.getId());

        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testFindProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Optional<Product> foundProduct = productService.findProductById(1L);

        assertTrue("Produto deveria ser encontrado", foundProduct.isPresent());
        assertEquals("Produto encontrado deveria ter o ID correto", 1L, foundProduct.get().getId().longValue());

        verify(productRepository, times(1)).findById(1L);
    }
}
