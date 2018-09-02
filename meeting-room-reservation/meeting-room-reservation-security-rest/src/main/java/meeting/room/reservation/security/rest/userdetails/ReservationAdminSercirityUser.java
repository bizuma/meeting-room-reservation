package meeting.room.reservation.security.rest.userdetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import meeting.room.reservation.domain.ReservationAdmin;

public class ReservationAdminSercirityUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReservationAdminSercirityUser(ReservationAdmin reservationAdmin) {
		super(reservationAdmin.getLoginId(),
				reservationAdmin.getPassword(), 
				makeGrantedAuthority(reservationAdmin.getRole()));
	}

	private static List<GrantedAuthority> makeGrantedAuthority(String role) {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority(role));
		return grantedAuthorityList;
	}
}
