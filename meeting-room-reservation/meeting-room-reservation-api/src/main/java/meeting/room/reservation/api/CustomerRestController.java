package meeting.room.reservation.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meeting.room.reservation.domain.Customer;
import meeting.room.reservation.repository.CustomerRepository;
import meeting.room.reservation.resource.CustomerResource;
import meeting.room.reservation.resource.mapper.CustomerResourceMapper;

/**
 * Customer Controller
 * 
 * @author bizuma
 *
 */
@RestController
@RequestMapping(value = "customer")
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerResourceMapper mapper;

	/**
	 * Customer 저장.
	 * 
	 * @param customer
	 * @return {@code ResponseEntity<CustomerResource>} 성공적으로 저장할경우
	 *         HttpStatus.CREATED
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<CustomerResource> save(@RequestBody Customer customer) {
		Customer savedCustomer = this.customerRepository.save(customer);

		return new ResponseEntity<>(mapper.map(savedCustomer), HttpStatus.CREATED);
	}

	/**
	 * Customer 목록 조회
	 * 
	 * @return {@code ResponseEntity<Collection<CustomerResource>>}
	 */
	@RequestMapping(method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<Collection<CustomerResource>> findAll() {
		Iterable<Customer> customerList = this.customerRepository.findAll();
		Collection<Customer> targetList = new ArrayList<>();
		customerList.forEach(targetList::add);
		return new ResponseEntity<>(mapper.mapCollection(targetList), HttpStatus.OK);
	}

	/**
	 * Customer 상세 조회
	 * 
	 * @return {@code ResponseEntity<CustomerResource>} id 에해당하는 Customer 가 존재하지
	 *         않을경우 {@link HttpStatus.NOT_FOUND} id 에 해당하는 Customer 이 존재할경우
	 *         {@link HttpStatus.OK}
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<CustomerResource> findById(@PathVariable long id) {
		Optional<Customer> customer = this.customerRepository.findById(id);
		if (!customer.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mapper.map(customer.get()), HttpStatus.OK);
	}

	/**
	 * Customer 수정
	 * 
	 * @param updatedCustomer
	 * @return {@code ResponseEntity<CustomerResource>} id 에해당하는 Customer 가 존재하지
	 *         않을경우 {@link HttpStatus.NOT_FOUND}, 수정이 완료되면 {@link HttpStatus.OK}
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<CustomerResource> update(@RequestBody Customer updatedCustomer) {

		Optional<Customer> existedCustomer = this.customerRepository.findById(updatedCustomer.getCustomerId());
		if (!existedCustomer.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Customer customer = existedCustomer.get();
		customer.setName(updatedCustomer.getName());

		return new ResponseEntity<>(mapper.map(this.customerRepository.save(customer)), HttpStatus.OK);
	}

	/**
	 * 
	 * Customer 삭제
	 * 
	 * @param id
	 * @return {@code ResponseEntity<Void>} id 에해당하는 Customer 가 존재하지 않을경우
	 *         {@link HttpStatus.NOT_FOUND}, 삭제가 완료되면 {@link HttpStatus.NO_CONTENT}
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@Transactional
	public ResponseEntity<Void> delete(@PathVariable long id) {
		Optional<Customer> existedCustomer = this.customerRepository.findById(id);
		if (!existedCustomer.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		this.customerRepository.delete(existedCustomer.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
