package meeting.room.reservation.resource;

import meeting.room.reservation.domain.Reservation;

public class ReservationResource {

	private Long reservationId;

	private String day;

	private String hour;

	private MeetingRoomResource meetingRoom;

	private CustomerResource customer;

	public ReservationResource(Reservation reservation) {
		this.reservationId = reservation.getReservationId();
		this.day = reservation.getDay();
		this.hour = reservation.getHour();
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public MeetingRoomResource getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(MeetingRoomResource meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public CustomerResource getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerResource customer) {
		this.customer = customer;
	}

}
