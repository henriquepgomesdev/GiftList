package com.giftlist.giftclient.repository;

import com.giftlist.GiftListApplication;
import com.giftlist.common.domain.People;
import com.giftlist.common.repository.PeopleRepository;
import com.giftlist.config.TestConfig;
import com.giftlist.giftclient.domain.GiftClient;
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
public class GiftClientRepositoryTest {

    @Autowired
    private GiftClientRepository giftClientRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Before
    public void setUp() {
        GiftClient giftClient1 = new GiftClient();
        People people1 = new People();
        people1.setName("Maria");
        people1.setCpf("98293333022");
        people1.setPhone("6299994471");
        peopleRepository.save(people1);
        giftClient1.setPeople(people1);
        giftClientRepository.save(giftClient1);

        GiftClient giftClient2 = new GiftClient();
        People people2 = new People();
        people2.setName("Talita");
        people2.setCpf("18897410006");
        people2.setPhone("6299994472");
        peopleRepository.save(people2);
        giftClient2.setPeople(people2);
        giftClientRepository.save(giftClient2);

        GiftClient giftClient3 = new GiftClient();
        People people3 = new People();
        people3.setName("Pedro");
        people3.setCpf("67319466058");
        people3.setPhone("6299994473");
        peopleRepository.save(people3);
        giftClient2.setPeople(people2);
        giftClientRepository.save(giftClient3);
    }

    @Test
    public void testSearchByName() {
        String name = "98293333022";
        List<GiftClient> giftClients = giftClientRepository.findAllByPeopleCpf(name);
        int expectedSize = 1;
        String message = String.format("Deveria encontrar %s clientes com '%s' com CPF", expectedSize, name);
        assertEquals(message, expectedSize, giftClients.size());
    }

}
