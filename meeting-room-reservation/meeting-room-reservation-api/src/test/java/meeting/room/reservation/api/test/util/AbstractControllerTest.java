package meeting.room.reservation.api.test.util;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ControllerTestBootApplication.class)
@ActiveProfiles("test")
@WebAppConfiguration
public abstract class AbstractControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@Before
	public void setupMockMvc() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected ResultActions get(String url, Object... urlVariables) throws Exception  {
		return mvc.perform(MockMvcRequestBuilders.get(url, urlVariables)
				.accept(MediaType.APPLICATION_JSON));
	}

	protected ResultActions post(String url, String content, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.post(url, urlVariables)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content));
	}

	protected ResultActions delete(String url, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.delete(url, urlVariables)
				.accept(MediaType.APPLICATION_JSON));
	}

	protected ResultActions put(String url, String content, Object... urlVariables) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.put(url, urlVariables)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content));
	}

	protected String toJsonString(final Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}