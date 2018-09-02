package meeting.room.reservation.security.rest.userdetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import meeting.room.reservation.domain.ReservationAdmin;
import meeting.room.reservation.repository.ReservationAdminRepository;

@Service
public class ReservationAdminDetailService implements UserDetailsService {
	
	@Autowired
	private ReservationAdminRepository reservationAdminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ReservationAdmin> existedReservationAdmin = this.reservationAdminRepository.findByLoginId(username);
		if (!existedReservationAdmin.isPresent()) {
			throw new UsernameNotFoundException("user is not exist.");
		}

		ReservationAdminSercirityUser securityUser = new ReservationAdminSercirityUser(existedReservationAdmin.get());
		return securityUser;
	}

}
