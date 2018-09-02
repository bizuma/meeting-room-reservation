package meeting.room.reservation.security.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import meeting.room.reservation.security.rest.RestAuthenticationEntryPoint;
import meeting.room.reservation.security.rest.authentication.RestLoginFailureHandler;
import meeting.room.reservation.security.rest.authentication.RestLoginSuccessHandler;
import meeting.room.reservation.security.rest.authentication.RestLogoutSuccessHandler;
import meeting.room.reservation.security.rest.userdetails.ReservationAdminDetailService;

public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/reservation/**").hasRole("ADMIN")
				.antMatchers("/customer/**").hasRole("ADMIN")
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

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new RestLoginSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new RestLoginFailureHandler();
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new RestLogoutSuccessHandler();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new ReservationAdminDetailService();
	}
}
