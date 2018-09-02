package meeting.room.reservation.api.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import meeting.room.reservation.api.test.util.AbstractControllerTest;
import meeting.room.reservation.domain.Customer;

public class CustomerRestControllerTest extends AbstractControllerTest {

	private static final String ROOT_URI = "/customer";

	@Test
	public void test_save_must_created() throws Exception {
		Customer customer = new Customer();
		customer.setName("labs");
		String body = super.toJsonString(customer);

		ResultActions resultActions = post(ROOT_URI, body);
		resultActions.andExpect(status().isCreated());
	}

	@Test
	public void test_findAll_must_ok() throws Exception {
		ResultActions resultActions = get(ROOT_URI);
		resultActions.andExpect(status().isOk());
	}

	@Test
	public void test_findById_must_not_found() throws Exception {
		long customerId = 0L;
		ResultActions resultActions = this.findCustomer(customerId);
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void test_findById_must_ok() throws Exception {
		long customerId = -101L;
		ResultActions resultActions = this.findCustomer(customerId);
		resultActions.andExpect(status().isOk());
	}
	
	@Test
	public void test_update_must_not_found() throws Exception {
		Customer customer = new Customer();
		customer.setName("labs");
		customer.setCustomerId(0L);
		
		String body = super.toJsonString(customer);
		ResultActions resultActions = put(ROOT_URI, body);
		resultActions.andExpect(status().isNotFound());
	}
	
	@Test
	public void test_update_must_ok() throws Exception {
		Customer customer = new Customer();
		customer.setName("labs");
		customer.setCustomerId(-100L);
		
		String body = super.toJsonString(customer);
		ResultActions resultActions = put(ROOT_URI, body);
		resultActions.andExpect(status().isOk());
	}
	
	@Test
	public void test_delete_must_no_content() throws Exception {
		ResultActions resultActions = delete(ROOT_URI + "/" + -100);
		resultActions.andExpect(status().isNoContent());
	}
	
	@Test
	public void test_delete_must_not_found() throws Exception {
		ResultActions resultActions = delete(ROOT_URI + "/" + 0);
		resultActions.andExpect(status().isNotFound());
	}

	private ResultActions findCustomer(long id) throws Exception {
		return get(ROOT_URI + "/" + id);
	}
}
