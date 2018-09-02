package meeting.room.reservation.resource.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import meeting.room.reservation.domain.MeetingRoom;
import meeting.room.reservation.resource.MeetingRoomResource;

@Component
public class MeetingRoomResourceMapper implements ResourceMapper<MeetingRoom, MeetingRoomResource> {

	@Override
	public MeetingRoomResource map(MeetingRoom domain) {
		MeetingRoomResource resource = new MeetingRoomResource(domain);
		return resource;
	}

	@Override
	public Collection<MeetingRoomResource> mapCollection(Collection<MeetingRoom> domainCollection) {
		return domainCollection.stream().map(o -> map(o)).collect(Collectors.toList());
	}

}
