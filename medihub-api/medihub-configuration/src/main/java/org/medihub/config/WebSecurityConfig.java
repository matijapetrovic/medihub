package org.medihub.config;

import lombok.RequiredArgsConstructor;
import org.medihub.web.security.TokenUtil;
import org.medihub.web.security.authentication.JWTAuthenticationEntryPoint;
import org.medihub.web.security.authentication.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final TokenUtil tokenUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Keep sessions stateless for the API
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // Process all unauthorized requests
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                // Allow all requests to the authentication endpoint
                .authorizeRequests()
                    .antMatchers("/auth/**").permitAll()
                    .antMatchers("/registration").permitAll()
                // For other endpoints, require authentication
                .anyRequest().authenticated().and()
                // Allow CORS for development
                .cors().and()
                // Do custom token based filtering
                .addFilterBefore(new JWTAuthenticationFilter(tokenUtil,userDetailsService),
                        BasicAuthenticationFilter.class);
        http.csrf().disable();
}

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
        web.ignoring().antMatchers(HttpMethod.POST, "/auth/activate/*");
        web.ignoring().antMatchers(HttpMethod.POST, "/registration");
    }
}
