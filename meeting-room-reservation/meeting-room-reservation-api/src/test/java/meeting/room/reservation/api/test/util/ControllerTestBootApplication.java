package meeting.room.reservation.api.test.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({ "meeting.room" })
@EnableJpaRepositories("meeting.room")
@EntityScan("meeting.room")
@SpringBootApplication
public class ControllerTestBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerTestBootApplication.class);
	}

}
