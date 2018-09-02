package meeting.room.reservation.repository;

import org.springframework.data.repository.CrudRepository;

import meeting.room.reservation.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
