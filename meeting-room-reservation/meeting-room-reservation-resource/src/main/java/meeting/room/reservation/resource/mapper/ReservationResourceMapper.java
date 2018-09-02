package meeting.room.reservation.resource.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import meeting.room.reservation.domain.Reservation;
import meeting.room.reservation.resource.ReservationResource;

@Component
public class ReservationResourceMapper implements ResourceMapper<Reservation, ReservationResource> {

	@Autowired
	private MeetingRoomResourceMapper meetingRoomResourceMapper;
	
	@Autowired
	private CustomerResourceMapper customerResourceMapper;
	
	@Override
	public ReservationResource map(Reservation domain) {
		ReservationResource resource = new ReservationResource(domain);
		resource.setMeetingRoom(this.meetingRoomResourceMapper.map(domain.getMeetingRoom()));
		resource.setCustomer(this.customerResourceMapper.map(domain.getCustomer()));
		return resource;
	}

	@Override
	public Collection<ReservationResource> mapCollection(Collection<Reservation> domainCollection) {
		return domainCollection.stream().map(o -> map(o)).collect(Collectors.toList());
	}

}
