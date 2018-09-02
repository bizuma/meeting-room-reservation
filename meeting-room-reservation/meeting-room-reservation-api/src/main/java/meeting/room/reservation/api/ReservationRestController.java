package meeting.room.reservation.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meeting.room.reservation.api.exception.MeetingRoomReserVationException;
import meeting.room.reservation.domain.Customer;
import meeting.room.reservation.domain.MeetingRoom;
import meeting.room.reservation.domain.Reservation;
import meeting.room.reservation.repository.CustomerRepository;
import meeting.room.reservation.repository.MeetingRoomRepository;
import meeting.room.reservation.repository.ReservationRepository;
import meeting.room.reservation.resource.ReservationResource;
import meeting.room.reservation.resource.mapper.ReservationResourceMapper;

/**
 * 
 * Reservation Controller
 * 
 * @author bizuma
 *
 */
@RestController
@RequestMapping(value = "reservation")
public class ReservationRestController {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ReservationResourceMapper mapper;

	/**
	 * Customer에 해당하는 Reservation 목록 조회
	 * 
	 * @return {@code ResponseEntity<Collection<ReservationResource>>} 예액목록이 존재할경우
	 *         HttStatus.OK, 존재하지 않을경우 HpptStatus.NOT_FOUND
	 * 
	 * @throws MeetingRoomReserVationException 선택한고객이 존재하지 않을경우
	 */
	@RequestMapping(value = "find-by-customer-id/{customerId}", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<Collection<ReservationResource>> findByCustomerId(@PathVariable long customerId) {
		Optional<Customer> existedCustomer = this.customerRepository.findById(customerId);
		if (!existedCustomer.isPresent()) {
			throw new MeetingRoomReserVationException("선택한 고객이 존재하지 않습니다.");
		}

		List<Reservation> reservationList = this.reservationRepository.findByCustomerId(customerId);
		if (reservationList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Collection<Reservation> targetList = new ArrayList<>();
		reservationList.forEach(targetList::add);
		return new ResponseEntity<>(mapper.mapCollection(targetList), HttpStatus.OK);
	}

	/**
	 * Reservation 상세 조회
	 * 
	 * @param id Reservation id
	 * @return {@code ResponseEntity<ReservationResource>} id 에해당하는 Reservation 이
	 *         존재하지 않을경우 {@link HttpStatus.NOT_FOUND} id 에 해당하는 Reservation 이 존재할경우
	 *         {@link HttpStatus.OK}
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@Transactional
	public ResponseEntity<ReservationResource> findById(@PathVariable long id) {
		Optional<Reservation> exsitedReservation = this.reservationRepository.findById(id);
		if (!exsitedReservation.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mapper.map(exsitedReservation.get()), HttpStatus.OK);
	}

	/**
	 * Reservation 저장
	 * 
	 * @param reservation
	 * @return {@code ResponseEntity<ReservationResource>}
	 * @throws MeetingRoomReserVationException 선택한 {@link MeetingRoom}이 존재하지 않을경우 ,
	 *                                         선택한 {@link Customer} 가 존재하지 않을경우
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ReservationResource> save(@RequestBody Reservation reservation) {
		if (reservation.getMeetingRoom().getMeetingRoomId() == null) {
			throw new MeetingRoomReserVationException("미팅룸이 입력되지 않았습니다.");
		}
		// meeting room check and setting
		Optional<MeetingRoom> existedMeetingRoom = this.meetingRoomRepository
				.findById(reservation.getMeetingRoom().getMeetingRoomId());
		if (!existedMeetingRoom.isPresent()) {
			throw new MeetingRoomReserVationException("예약하려는 미팅룸이 존재하지 않습니다.");
		}
		reservation.setMeetingRoom(existedMeetingRoom.get());

		// customer check and setting
		if (reservation.getCustomer().getCustomerId() == null) {
			throw new MeetingRoomReserVationException("고객정보가 입력되지 않았습니다.");
		}

		Optional<Customer> existedCustomer = this.customerRepository
				.findById(reservation.getCustomer().getCustomerId());
		if (!existedCustomer.isPresent()) {
			throw new MeetingRoomReserVationException("예약하려는 고객이 존재하지않습니다.");
		}
		reservation.setCustomer(existedCustomer.get());

		Reservation savedReservation = this.reservationRepository.save(reservation);
		return new ResponseEntity<>(mapper.map(savedReservation), HttpStatus.CREATED);
	}

}
