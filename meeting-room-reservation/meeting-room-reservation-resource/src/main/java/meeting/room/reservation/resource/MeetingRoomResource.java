package meeting.room.reservation.resource;

import meeting.room.reservation.domain.MeetingRoom;

public class MeetingRoomResource {

	private Long meetingRoomId;

	private String name;

	public MeetingRoomResource(MeetingRoom meetingRoom) {
		this.meetingRoomId = meetingRoom.getMeetingRoomId();
		this.name = meetingRoom.getName();
	}

	public Long getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(Long meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
