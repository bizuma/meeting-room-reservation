package meeting.room.reservation.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import meeting.room.reservation.domain.Customer;
import meeting.room.reservation.domain.MeetingRoom;
import meeting.room.reservation.domain.Reservation;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReservationRepositoryTest extends AbstractRepositoryTest {

	private static final long NON_EXIST_ID = 10L;

	private static final long IMPORTED_INITIAL_ID = -300L;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void test1_find_must_none_exist() {
		Assert.assertFalse(this.reservationRepository.existsById(NON_EXIST_ID));
	}

	@Test
	public void test2_find_must_exist() {
		Assert.assertTrue(this.reservationRepository.findById(IMPORTED_INITIAL_ID).isPresent());
	}

	public void test3_find_all() {
		Assert.assertEquals(4, this.reservationRepository.count());
	}

	@Test
	public void test4_save_reservation_and_find_must_exist() {
		Reservation reservation = new Reservation();
		reservation.setDay("20190101");
		reservation.setHour("13");

		// meeting room setting
		long meetingRoomId = -201L;
		Optional<MeetingRoom> existedMeetingRoom = this.meetingRoomRepository.findById(meetingRoomId);
		Assert.assertTrue(existedMeetingRoom.isPresent());

		reservation.setMeetingRoom(existedMeetingRoom.get());

		// customer setting
		long customerId = -100L;
		Optional<Customer> existedCustomer = this.customerRepository.findById(customerId);
		Assert.assertTrue(existedCustomer.isPresent());

		reservation.setCustomer(existedCustomer.get());

		// save and assert id
		Reservation savedReservation = this.reservationRepository.save(reservation);
		Assert.assertTrue(savedReservation.getReservationId() > 0);

		Assert.assertEquals(5, this.reservationRepository.count());
	}

	@Test
	public void test5_update() {
		Optional<Reservation> existedReservation = this.reservationRepository.findById(IMPORTED_INITIAL_ID);
		Assert.assertTrue(existedReservation.isPresent());

		Reservation reservation = existedReservation.get();
		reservation.setDay("20191010");

		Assert.assertEquals("20191010", this.reservationRepository.save(reservation).getDay());
	}

	@Test
	public void test6_delete_reservation_by_id_and_find_must_not_exist() {
		this.reservationRepository.deleteById(IMPORTED_INITIAL_ID);
		Assert.assertFalse(this.reservationRepository.existsById(IMPORTED_INITIAL_ID));
	}

}
