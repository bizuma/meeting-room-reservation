package meeting.room.reservation.security.rest.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SecurityTestBootApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class SecurityTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@Before
	public void setupMockMvc() throws Exception {
		this.mvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
					.apply(springSecurity())
					.alwaysDo(print())
				.build();
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void test_get_must_ok() throws Exception {
		mvc.perform(get("/security-sample/hello"))
		.andExpect(status().isOk());
	}

	@Test
	public void test_get_must_unauthorized() throws Exception {
		mvc.perform(get("/security-sample/hello"))
		.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "guest", password = "guest", roles = "GUEST")
	public void test_get_must_forbidden() throws Exception {
		mvc.perform(get("/security-sample/hello").with(user("guest")))
		.andExpect(status().isForbidden());
	}

	@Test
	public void test_authenticationSuccess() throws Exception {
		mvc.perform(formLogin("/auth/login-process").user("username", "admin").password("password", "admin"))
				.andExpect(status().isOk());
	}

	@Test
	public void test_authenticationFail() throws Exception {
		mvc.perform(formLogin("/auth/login-process").user("username", "none_exist_user").password("password", "admin"))
				.andExpect(status().isUnauthorized());
	}
}
