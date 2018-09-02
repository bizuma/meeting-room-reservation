
package meeting.room.reservation.app.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import meeting.room.reservation.security.rest.config.DefaultSecurityConfig;

/**
 * Meeting Room Reservation Security Config
 * 
 * @author bizuma
 *
 */
@EnableWebSecurity
public class SecurityConfig extends DefaultSecurityConfig {

}
