package meeting.room.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import meeting.room.reservation.domain.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	@Query("delete from Reservation r where r.meetingRoom.id = :meetingRoomId")
	@Modifying
	void deleteByMeetingRoom(@Param("meetingRoomId") long meetingRoomId);
	
	@Query("select r from Reservation r where r.customer.customerId = :customerId")
	List<Reservation> findByCustomerId(@Param("customerId") long customerId);
}
