package meeting.room.reservation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import meeting.room.reservation.domain.ReservationAdmin;

public interface ReservationAdminRepository extends CrudRepository<ReservationAdmin, Long> {

	Optional<ReservationAdmin> findByLoginId(String loginId);
	
}
