package com.gymapplicationbackend.gymapplicationbackendapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gymapplicationbackend.gymapplicationbackendapi.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final DataSource dataSource;
//    private final ObjectMapper objectMapper;
//    private final RestAuthenticationFailureHandler failureHandler;
//    private final RestAuthenticationSuccessHandler successHandler;
//    private final String secret;
//
//    public SecurityConfig(DataSource dataSource, ObjectMapper objectMapper, RestAuthenticationFailureHandler failureHandler,
//                          RestAuthenticationSuccessHandler successHandler, @Value("${jwt.secret}") String secret) {
//
//        this.dataSource = dataSource;
//        this.objectMapper = objectMapper;
//        this.failureHandler = failureHandler;
//        this.successHandler = successHandler;
//        this.secret = secret;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .withDefaultSchema()
//                .dataSource(dataSource)
//        .withUser("test")
//        .password("{bcrypt}" + new BCryptPasswordEncoder().encode("test"))
//        .roles("USER");
//    }

    @Autowired
    private RedisService redisService;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList((AuthenticationProviderImpl) new AuthenticationProviderImpl(redisService)));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(authenticationFilter())
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsManager(), secret))
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JWTAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .headers().frameOptions().disable();
    }

//    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
//        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
//        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
//        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
//        authenticationFilter.setAuthenticationManager(super.authenticationManager());
//        return authenticationFilter;
//    }
//
//    @Bean
//    public UserDetailsManager userDetailsManager(){
//        return new JdbcUserDetailsManager(dataSource);
//    }
}
