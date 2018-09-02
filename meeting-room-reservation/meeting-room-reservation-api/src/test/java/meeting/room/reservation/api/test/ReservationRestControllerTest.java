package meeting.room.reservation.api.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import meeting.room.reservation.api.exception.MeetingRoomReserVationException;
import meeting.room.reservation.api.test.util.AbstractControllerTest;
import meeting.room.reservation.domain.Customer;
import meeting.room.reservation.domain.MeetingRoom;
import meeting.room.reservation.domain.Reservation;

public class ReservationRestControllerTest extends AbstractControllerTest {

	private static final String ROOT_URI = "/reservation";
	
	private static final long RESERVATED_CUSTOMER_ID = -100L;
	private static final long NOT_RESERVATED_CUSTOMER_ID = -102L;
	private static final long NOT_EXISTED_CUSTOMER_ID = -999L;
	
	private static final long EXISTED_RESERVATION_ID = -300L;
	private static final long NOT_EXISTED_RESERVATION_ID = -999L;

	@Test
	public void test_findByCustomerId_must_ok() throws Exception {
		ResultActions resultActions = get(ROOT_URI + "/find-by-customer-id/" + RESERVATED_CUSTOMER_ID);
		resultActions.andExpect(status().isOk());
	}

	@Test
	public void test_findByCustomerId_must_not_found() throws Exception {
		ResultActions resultActions = get(ROOT_URI + "/find-by-customer-id/" + NOT_RESERVATED_CUSTOMER_ID);
		resultActions.andExpect(status().isNotFound());
	}
	
	@Test
	public void test_findByCustomerId_must_throw_MeetingRoomReserVationException() {
		try {
			get(ROOT_URI + "/find-by-customer-id/" + NOT_EXISTED_CUSTOMER_ID);
			Assert.fail("must throw MeetingRoomReserVationException ");
		} catch (Exception e) {
			Assert.assertTrue(e.getCause() instanceof MeetingRoomReserVationException);
		}
	}
	
	@Test
	public void test_findById_must_ok() throws Exception {
		ResultActions resultActions = get(ROOT_URI + "/" + EXISTED_RESERVATION_ID);
		resultActions.andExpect(status().isOk());
	}
	
	@Test
	public void test_findById_must_not_found() throws Exception {
		ResultActions resultActions = get(ROOT_URI + "/" + NOT_EXISTED_RESERVATION_ID);
		resultActions.andExpect(status().isNotFound());
	}
	
	@Test
	public void test_save_must_throw_MeetingRoomReserVationException_when_meetinRoomId_is_null() {
		try {
			Reservation reservation = this.makeSimpleReservation();
			reservation.setMeetingRoom(new MeetingRoom());
			
			String body = super.toJsonString(reservation);
			post(ROOT_URI, body);
			Assert.fail("must throw MeetingRoomReserVationException ");
		} catch (Exception e) {
			Assert.assertTrue(e.getCause() instanceof MeetingRoomReserVationException);
			Assert.assertEquals("미팅룸이 입력되지 않았습니다.", e.getCause().getMessage());
		}
	}
	
	@Test
	public void test_save_must_throw_MeetingRoomReserVationException_when_meetinRoomId_is_not_exist() {
		try {
			Reservation reservation = this.makeSimpleReservation();
			MeetingRoom meetingRoom = new MeetingRoom();
			meetingRoom.setMeetingRoomId(-999L);

			reservation.setMeetingRoom(meetingRoom);

			String body = super.toJsonString(reservation);
			post(ROOT_URI, body);
			Assert.fail("must throw MeetingRoomReserVationException ");
		} catch (Exception e) {
			Assert.assertTrue(e.getCause() instanceof MeetingRoomReserVationException);
			Assert.assertEquals("예약하려는 미팅룸이 존재하지 않습니다.", e.getCause().getMessage());
		}
	}
	
	@Test
	public void test_save_must_throw_MeetingRoomReserVationException_when_customerId_is_null() {
		try {
			Reservation reservation = this.makeSimpleReservation();
			MeetingRoom meetingRoom = new MeetingRoom();
			meetingRoom.setMeetingRoomId(-200L);
			reservation.setMeetingRoom(meetingRoom);
			
			reservation.setCustomer(new Customer());
			
			String body = super.toJsonString(reservation);
			post(ROOT_URI, body);
			Assert.fail("must throw MeetingRoomReserVationException ");
		} catch (Exception e) {
			Assert.assertTrue(e.getCause() instanceof MeetingRoomReserVationException);
			Assert.assertEquals("고객정보가 입력되지 않았습니다.", e.getCause().getMessage());
		}
	}
	
	@Test
	public void test_save_must_throw_MeetingRoomReserVationException_when_customerId_is_not_exist() {
		try {
			Reservation reservation = this.makeSimpleReservation();
			MeetingRoom meetingRoom = new MeetingRoom();
			meetingRoom.setMeetingRoomId(-200L);
			reservation.setMeetingRoom(meetingRoom);
			
			Customer customer = new Customer();
			customer.setCustomerId(-999L);
			reservation.setCustomer(customer);
			
			String body = super.toJsonString(reservation);
			post(ROOT_URI, body);
			Assert.fail("must throw MeetingRoomReserVationException ");
		} catch (Exception e) {
			Assert.assertTrue(e.getCause() instanceof MeetingRoomReserVationException);
			Assert.assertEquals("예약하려는 고객이 존재하지않습니다.", e.getCause().getMessage());
		}
	}
	
	@Test
	public void test_save_must_created() throws Exception {
		Reservation reservation = this.makeSimpleReservation();
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setMeetingRoomId(-200L);
		reservation.setMeetingRoom(meetingRoom);

		Customer customer = new Customer();
		customer.setCustomerId(-100L);
		reservation.setCustomer(customer);

		String body = super.toJsonString(reservation);
		ResultActions resultActions = post(ROOT_URI, body);
		resultActions.andExpect(status().isCreated());		
	}
	
	private Reservation makeSimpleReservation() {
		Reservation reservation = new Reservation();
		reservation.setDay("20190101");
		reservation.setHour("12");
		return reservation;
	}
}
