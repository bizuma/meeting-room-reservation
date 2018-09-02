package meeting.room.reservation.repository;

import org.springframework.data.repository.CrudRepository;

import meeting.room.reservation.domain.MeetingRoom;

public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Long> {

}
