package ca.sheridancollege.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		
		.antMatchers("/ap/**").hasRole("EMPLOYER")
		.antMatchers("/showjobs/**").hasAnyRole("EMPLOYER","STUDENT")
		.antMatchers("/Dummy/**").hasRole("EMPLOYER")
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.antMatchers("/showList/**").hasAnyRole("EMPLOYER","STUDENT")
		.antMatchers("/editLink/{id}/**").hasRole("EMPLOYER")




		.antMatchers("/",
				"/css/**",
				"/js/**",
				"/img/**",
				"/**","/Job/**").permitAll().anyRequest().authenticated()

		.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserDetailedServiceImpl userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
			throws Exception{

		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}



}