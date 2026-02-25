import com.giftlist.GiftListApplication;
import com.giftlist.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GiftListApplication.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "logging.level.org.springframework=DEBUG",
        "logging.level.org.hibernate=DEBUG",
        "spring.jpa.show-sql=true"
})
public class SimpleIntegrationTest {

    @Test
    public void contextLoads() {
    }
}
