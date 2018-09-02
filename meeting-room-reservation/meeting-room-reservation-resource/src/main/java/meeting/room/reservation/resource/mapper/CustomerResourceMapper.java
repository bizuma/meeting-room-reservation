package meeting.room.reservation.resource.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import meeting.room.reservation.domain.Customer;
import meeting.room.reservation.resource.CustomerResource;

@Component
public class CustomerResourceMapper implements ResourceMapper<Customer, CustomerResource> {

//	@Autowired
//	protected EntityLinks entityLinks;

	@Override
	public CustomerResource map(Customer domain) {
		CustomerResource resource = new CustomerResource(domain);
//
//		final Link selfLink = entityLinks.linkToSingleResource(resource);
//		resource.add(selfLink.withSelfRel());
//		resource.add(selfLink.withRel("update"));
//		resource.add(selfLink.withRel("delete"));
		return resource;
	}

	@Override
	public Collection<CustomerResource> mapCollection(Collection<Customer> domainCollection) {
		return domainCollection.stream().map(o -> map(o)).collect(Collectors.toList());
	}

}
