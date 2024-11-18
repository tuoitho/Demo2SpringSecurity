package com.demo2springsecurity;

//import com.demo.service.ShopmeUserDetailsService;

import com.demo2springsecurity.repository.UserInfoRepository;
import com.demo2springsecurity.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserInfoRepository userDetailsService;
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
//        return http.build();
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/vertretungsplan").hasAnyRole("SCHUELER", "LEHRER", "VERWALTUNG")
//                        .requestMatchers("/a").permitAll()
////                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
////                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll());
//
//        return http.build();
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/new").permitAll() // với endpoint /ignore1 và /ignore2 thì sẽ được cho qua
                        .requestMatchers("/").permitAll() // với endpoint / thì sẽ được cho qua
                        .requestMatchers("/a").authenticated() //
//                        .requestMatchers("/customer/**").authenticated() // với endpoint /customer/** sẽ yêu cầu authenticate
                        .anyRequest().authenticated() // tất cả các request khác cũng yêu cầu authenticate
                )
                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
                .build();
    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
//    }



}