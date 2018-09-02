package meeting.room.reservation.repository;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import meeting.room.reservation.test.util.RepositoryTestConfiguration;

/**
 * Repository Test 추상 클래스
 * 
 * @author bizuma
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RepositoryTestConfiguration.class)
@ActiveProfiles("test")
@Transactional
public abstract class AbstractRepositoryTest {

}
