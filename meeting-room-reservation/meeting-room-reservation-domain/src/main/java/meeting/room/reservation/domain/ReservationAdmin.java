package meeting.room.reservation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Reservation 관리자
 * 
 * @author bizuma
 *
 */
@Entity
@Table(name = "RESERVATION_ADMIN")
public class ReservationAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "LOGIN_ID", length = 50, nullable = false)
	private String loginId;

	@Column(name = "PASSWORD", length = 200, nullable = false)
	private String password;

	@Column(name = "ROLE", length = 20, nullable = false)
	private String role;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
