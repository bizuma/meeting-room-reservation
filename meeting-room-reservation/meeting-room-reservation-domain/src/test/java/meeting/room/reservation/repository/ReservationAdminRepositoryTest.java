package meeting.room.reservation.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import meeting.room.reservation.domain.ReservationAdmin;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservationAdminRepositoryTest extends AbstractRepositoryTest {

	private static final String EXISTED_LOGIN_ID = "admin";

	@Autowired
	private ReservationAdminRepository reservationAdminReposutory;

	@Test
	public void test1_findByLoginId_must_exist() {
		Optional<ReservationAdmin> reservationAdmin = this.reservationAdminReposutory.findByLoginId(EXISTED_LOGIN_ID);
		Assert.assertTrue(reservationAdmin.isPresent());
	}
}
