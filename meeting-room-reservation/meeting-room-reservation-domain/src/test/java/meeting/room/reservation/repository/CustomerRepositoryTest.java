package meeting.room.reservation.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import meeting.room.reservation.domain.Customer;

/**
 * CustomerRepository Test
 * 
 * @author bizuma
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRepositoryTest extends AbstractRepositoryTest {

	private static final long NON_EXIST_ID = 10L;

	private static final long IMPORTED_INITIAL_ID = -100L;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void test1_existsById_must_none_exist() {
		Assert.assertFalse(this.customerRepository.existsById(NON_EXIST_ID));
	}

	@Test
	public void test2_findById_must_exist() {
		Assert.assertTrue(this.customerRepository.findById(IMPORTED_INITIAL_ID).isPresent());
	}

	public void test3_count() {
		Assert.assertEquals(2, this.customerRepository.count());
	}

	@Test
	public void test4_save_and_count() {
		Customer customer = new Customer();
		customer.setName("labs");

		Customer savedCustomer = this.customerRepository.save(customer);
		Assert.assertTrue(savedCustomer.getCustomerId() > 0);

		Assert.assertEquals(4, this.customerRepository.count());
	}

	@Test
	public void test5_update() {
		Optional<Customer> existedCustomer = this.customerRepository.findById(IMPORTED_INITIAL_ID);
		Assert.assertTrue(existedCustomer.isPresent());

		Customer customer = existedCustomer.get();
		customer.setName("modified-labs");

		Assert.assertEquals("modified-labs", this.customerRepository.save(customer).getName());
	}

	@Test
	public void test6_delete_and_find_must_not_exist() {
		this.customerRepository.deleteById(IMPORTED_INITIAL_ID);

		Assert.assertFalse(this.customerRepository.existsById(IMPORTED_INITIAL_ID));
	}
}
