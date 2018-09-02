package meeting.room.reservation.resource.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import meeting.room.reservation.domain.ReservationAdmin;
import meeting.room.reservation.resource.ReservationAdminResource;

@Component
public class ReservationAdminResourceMapper implements ResourceMapper<ReservationAdmin, ReservationAdminResource> {

	@Override
	public ReservationAdminResource map(ReservationAdmin domain) {
		ReservationAdminResource resource = new ReservationAdminResource(domain);
//
//		final Link selfLink = entityLinks.linkToSingleResource(resource);
//		resource.add(selfLink.withSelfRel());
//		resource.add(selfLink.withRel("update"));
//		resource.add(selfLink.withRel("delete"));
		return resource;
	}

	@Override
	public Collection<ReservationAdminResource> mapCollection(Collection<ReservationAdmin> domainCollection) {
		return domainCollection.stream().map(o -> map(o)).collect(Collectors.toList());
	}

}
