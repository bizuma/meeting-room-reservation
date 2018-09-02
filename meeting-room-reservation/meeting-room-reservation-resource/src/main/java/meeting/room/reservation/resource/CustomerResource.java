package meeting.room.reservation.resource;

import meeting.room.reservation.domain.Customer;

public class CustomerResource /*extends ResourceSupport*/ {

	private long customerId;

	private String name;

	public CustomerResource(Customer customer) {
		this.customerId = customer.getCustomerId();
		this.name = customer.getName();
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
