package meeting.room.reservation.security.rest.test;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import meeting.room.reservation.security.rest.config.DefaultSecurityConfig;

/**
 * 
 * @author bizuma
 *
 */
@EnableWebSecurity
public class TestSecurityConfig extends DefaultSecurityConfig {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/security-sample/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
				.and()
					.formLogin()
						.loginProcessingUrl("/auth/login-process")
						.usernameParameter("username")
						.passwordParameter("password")
						.successHandler(authenticationSuccessHandler())
						.failureHandler(authenticationFailureHandler())
				.and()
					.logout()
						.logoutUrl("/autu/logout-process")
						.logoutSuccessHandler(logoutSuccessHandler())
				.and()
					.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
	}
}
