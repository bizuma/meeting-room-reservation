package meeting.room.reservation.security.rest.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "security-sample")
public class SecuritySampleTestController {

	@RequestMapping(value="hello", method = RequestMethod.GET)
	public ResponseEntity<String> doSomething() {
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
}
