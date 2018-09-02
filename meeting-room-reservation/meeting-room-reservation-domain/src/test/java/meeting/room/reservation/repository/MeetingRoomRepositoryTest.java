package meeting.room.reservation.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import meeting.room.reservation.domain.MeetingRoom;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingRoomRepositoryTest extends AbstractRepositoryTest {

	private static final long NON_EXIST_ID = 10L;

	private static final long IMPORTED_INITIAL_ID = -200L;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	@Autowired
	private ReservationRepository reversationRepository;

	@Test
	public void test1_existsById_must_none_exist() {
		Assert.assertFalse(this.meetingRoomRepository.existsById(NON_EXIST_ID));
	}

	@Test
	public void test2_findById_must_exist() {
		Assert.assertTrue(this.meetingRoomRepository.findById(IMPORTED_INITIAL_ID).isPresent());
	}

	public void test3_count() {
		Assert.assertEquals(2, this.meetingRoomRepository.count());
	}

	@Test
	public void test4_save_and_count() {
		MeetingRoom meetingRoom = new MeetingRoom();
		meetingRoom.setName("특실");

		MeetingRoom savedMeetingRoom = this.meetingRoomRepository.save(meetingRoom);
		Assert.assertTrue(savedMeetingRoom.getMeetingRoomId() > 0);
	
		Assert.assertEquals(3, this.meetingRoomRepository.count());
	}

	@Test
	public void test5_update() {
		Optional<MeetingRoom> existedMeetingRoom = this.meetingRoomRepository.findById(IMPORTED_INITIAL_ID);
		Assert.assertTrue(existedMeetingRoom.isPresent());

		MeetingRoom meetingRoom = existedMeetingRoom.get();
		meetingRoom.setName("특대실");

		Assert.assertEquals("특대실", this.meetingRoomRepository.save(meetingRoom).getName());
	}

	@Test
	public void test6_delete_and_find_must_not_exist() {
		// delete reservation
		this.reversationRepository.deleteByMeetingRoom(IMPORTED_INITIAL_ID);

		// delete meeting room
		this.meetingRoomRepository.deleteById(IMPORTED_INITIAL_ID);

		Assert.assertFalse(this.meetingRoomRepository.existsById(IMPORTED_INITIAL_ID));
	}
}
