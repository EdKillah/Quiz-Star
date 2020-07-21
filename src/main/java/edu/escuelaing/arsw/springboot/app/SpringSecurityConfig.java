package edu.escuelaing.arsw.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import edu.escuelaing.arsw.springboot.app.models.services.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/","/css/**","/js/**","/index","/play/**","/crear_preguntas","/crear_tema").permitAll()
		.antMatchers("/preguntas","/temas","/usuarios","/uploads/**").authenticated()
		//.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
	}
	

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

		System.out.println("Cuantas veces entra por aca");
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);

	}

}
