package sit.int221.oasip.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sit.int221.oasip.entities.User;
import sit.int221.oasip.filter.CustomAccessDeniedHandler;
import sit.int221.oasip.filter.CustomAuthenticationFilter;
import sit.int221.oasip.filter.CustomAuthorizationFilter;
import sit.int221.oasip.repositories.UserRepository;

import java.util.List;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private Argon2PasswordEncoder argon2;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, Argon2PasswordEncoder argon2) {
        this.userDetailsService = userDetailsService;
        this.argon2 = argon2;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(argon2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests().antMatchers("/api/auth/refresh_token" ,"/api/auth/login","/api/auth/check" , "/api/home/events" , "/api/home/category").permitAll()
                .and().cors()
                .and().authorizeHttpRequests().antMatchers( "/api/users/**" , "/api/auth/match").hasAuthority("admin")
                .and().authorizeHttpRequests().antMatchers("/api/category/**").hasAnyAuthority("lecturer" , "admin")
                .and().authorizeHttpRequests().anyRequest().authenticated()
                .and().addFilter(customAuthenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter() , UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {return super.authenticationManagerBean();}

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}
