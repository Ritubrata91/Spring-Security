package com.ritubrata.springsecurityjdbc;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;	
    
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//using default datasource(here it is H2) and then create default schema. 
		//Then it create two user by its own as per info provided)
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource) .withDefaultSchema()
		 * .withUser( User.withUsername("user") .password("pass") .roles("USER"))
		 * .withUser( User.withUsername("admin") .password("pass") .roles("ADMIN"));
		 */
		
		auth.jdbcAuthentication()
		.dataSource(dataSource);
		
		// if you have different table name or attribute name for user and authorities
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource)
		 * .usersByUsernameQuery("select username , password, enabled " + "from users "
		 * + "where username = ?")
		 * .authoritiesByUsernameQuery("select username , authority " +
		 * "from authorities " + "where username = ?");
		 */
		
		/*
		 * you can set up different database in application.properties like
		 * spring.datasource.url = "" spring.datasource.username = ""
		 * spring.datasource.password =""
		 */
    	
       
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
