package meeting.room.reservation.test.util;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("meeting.room")
@EnableJpaRepositories("meeting.room")
@EntityScan("meeting.room")
//@ConfigurationProperties(locations = "classpath:server.yml", prefix = "test")
@SpringBootApplication
public class RepositoryTestConfiguration {

}
