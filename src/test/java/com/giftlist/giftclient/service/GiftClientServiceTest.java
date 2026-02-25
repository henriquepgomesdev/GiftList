package com.giftlist.giftclient.service;

import com.giftlist.common.domain.People;
import com.giftlist.giftclient.domain.GiftClient;
import com.giftlist.giftclient.repository.GiftClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GiftClientServiceTest {

    @Mock
    private GiftClientRepository giftClientRepository;

    @InjectMocks
    private GiftClientServiceImpl giftClientService;

    private GiftClient giftClient1;
    private GiftClient giftClient2;
    private GiftClient giftClient3;

    private People people1;
    private People people2;
    private People people3;

    @Before
    public void setUp() {


        giftClient1 = new GiftClient();
        giftClient1.setId(1L);
        people1 = new People();
        people1.setName("Client 1");
        people1.setCpf("98293333022");
        giftClient1.setPeople(people1);

        giftClient2 = new GiftClient();
        giftClient2.setId(2L);
        people2 = new People();
        people2.setName("Client 2");
        people2.setCpf("18897410006");
        giftClient2.setPeople(people2);

        giftClient3 = new GiftClient();
        giftClient3.setId(3L);
        people3 = new People();
        people3.setName("Client 3");
        people3.setCpf("67319466058");
        giftClient2.setPeople(people2);
    }

    @Test
    public void testSaveProduct() {
        when(giftClientRepository.save(any(GiftClient.class))).thenReturn(giftClient1);

        GiftClient giftClientResult1 = giftClientService.saveGiftClient(this.giftClient1);

        assertNotNull("O cliente salvo não deveria ser nulo", giftClientResult1);
        assertEquals("O cliente salvo deveria ter o mesmo ID", this.giftClient1.getId(), giftClientResult1.getId());

        verify(giftClientRepository, times(1)).save(this.giftClient1);

        when(giftClientRepository.save(any(GiftClient.class))).thenReturn(giftClient2);

        GiftClient giftClientResult2 = giftClientService.saveGiftClient(this.giftClient2);

        assertNotNull("O cliente salvo não deveria ser nulo", giftClientResult2);
        assertEquals("O cliente salvo deveria ter o mesmo ID", this.giftClient2.getId(), giftClientResult2.getId());

        verify(giftClientRepository, times(1)).save(this.giftClient2);

        when(giftClientRepository.save(any(GiftClient.class))).thenReturn(giftClient3);

        GiftClient giftClientResult3 = giftClientService.saveGiftClient(this.giftClient3);

        assertNotNull("O cliente salvo não deveria ser nulo", giftClientResult3);
        assertEquals("O cliente salvo deveria ter o mesmo ID", this.giftClient3.getId(), giftClientResult3.getId());

        verify(giftClientRepository, times(1)).save(this.giftClient3);
    }

    @Test
    public void testFindProductById() {
        when(giftClientRepository.findById(1L)).thenReturn(Optional.of(giftClient1));

        Optional<GiftClient> foundGiftClient = giftClientService.findGiftClientById(1L);

        assertTrue("O cliente deveria ser encontrado", foundGiftClient.isPresent());
        assertEquals("O cliente encontrado deveria ter o ID correto", 1L, foundGiftClient.get().getId().longValue());

        verify(giftClientRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAll() {
        when(giftClientRepository.findAll()).thenReturn(Arrays.asList(giftClient1, giftClient2, giftClient3));

        List<GiftClient> foundGiftClients = giftClientService.findAllGiftClients();

        assertEquals("O cliente salvo deveria ter o mesmo ID", 3, foundGiftClients.size());
    }
}
