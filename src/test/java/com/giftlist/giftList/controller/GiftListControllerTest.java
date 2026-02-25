package com.giftlist.giftList.controller;

import com.giftlist.giftlist.controller.GiftListController;
import com.giftlist.giftlist.domain.GiftList;
import com.giftlist.giftlist.service.GiftListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class GiftListControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GiftListService giftListService;

    @InjectMocks
    private GiftListController giftListController;

    private GiftList product1;
    private GiftList product2;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(giftListController).build();

        product1 = new GiftList();
        product1.setId(1L);
        product1.setName("Lista casa nova");

        product2 = new GiftList();
        product2.setId(2L);
        product2.setName("Lista casamento");

    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(giftListService.findAllGiftLists()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/gift-list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Lista casa nova")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Lista casamento")));

        verify(giftListService, times(1)).findAllGiftLists();
    }
}
