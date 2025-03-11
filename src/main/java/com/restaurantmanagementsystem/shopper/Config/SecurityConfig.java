//    package com.restaurantmanagementsystem.shopper.Config;
//
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//    import org.springframework.security.core.Authentication;
//    import org.springframework.security.core.userdetails.User;
//    import org.springframework.security.core.userdetails.UserDetails;
//    import org.springframework.security.core.userdetails.UserDetailsService;
//    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//    import org.springframework.security.crypto.password.PasswordEncoder;
//    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//    import org.springframework.security.web.SecurityFilterChain;
//    import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//    import org.springframework.web.cors.CorsConfiguration;
//    import org.springframework.web.cors.CorsConfigurationSource;
//    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//    import jakarta.servlet.ServletException;
//    import jakarta.servlet.http.HttpServletRequest;
//    import jakarta.servlet.http.HttpServletResponse;
//    import java.io.IOException;
//    import java.util.Arrays;
//    import java.util.Set;
//
//    import static org.springframework.security.config.Customizer.withDefaults;
//
//    @Configuration
//    @EnableWebSecurity
//    public class SecurityConfig {
//        @Bean
//        public BCryptPasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//
//        }
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(AbstractHttpConfigurer::disable)
//                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Enable CORS
//                    .authorizeHttpRequests(authz -> authz
//                            .requestMatchers("/admin/**").hasRole("ADMIN")
//                            .requestMatchers("/register", "/css/**", "/js/**", "/images/**", "/admin/menuItem/api/list").permitAll()
//                            .requestMatchers("/register", "/css/**", "/js/**", "/images/**", "/admin/menu/api/list", "/about","/contact","/").permitAll()
//
//                            .requestMatchers("/shopper/**").hasRole("SHOPPER")
//                            .requestMatchers("/register", "/css/**", "/js/**", "/images/**","/shopper/menu", "/shopper/menuItem/api/list").permitAll()
//                            .requestMatchers("/register", "/css/**", "/js/**", "/images/**", "/shopper/menu","/shopper/menu/api/list", "/about","/contact","/").permitAll()
//                            .requestMatchers("/").permitAll()
//                            .anyRequest().authenticated()
//                    )
//                    .formLogin(form -> form
//                            .loginPage("/login")
//                            .successHandler(successHandler()) // Use a custom success handler
//                            .permitAll()
//                    )
//                    .logout(logout -> logout
//                            .permitAll())
//                    .httpBasic(withDefaults());
//
//            return http.build();
//        }
//
//        @Bean
//        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//            UserDetails shopper = User.builder()
//                    .username("shopper")
//                    .password(passwordEncoder.encode("shopper"))
//                    .roles("SHOPPER")
//                    .build();
//
//
//
//            UserDetails admin = User.builder()
//                    .username("admin")
//                    .password(passwordEncoder.encode("admin"))
//                    .roles("ADMIN")
//                    .build();
//
//          return new InMemoryUserDetailsManager(shopper, admin);
//        }
//
//        @Bean
//        public AuthenticationSuccessHandler successHandler() {
//            return new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                    Authentication authentication) throws IOException, ServletException {
//                    Set<String> roles = org.springframework.security.core.authority.AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//                    System.out.println("Authentication Principal: " + authentication.getPrincipal());  // Log principal
//                    System.out.println("Authentication Authorities: " + roles); // Log authorities
//                    if (roles.contains("ROLE_ADMIN")) {
//                        response.sendRedirect("/admin/menu");
//                    } else if (roles.contains("ROLE_SHOPPER")) {
//                        response.sendRedirect("/shopper/menu");
//                    } else {
//                        response.sendRedirect("/"); // Default redirect
//                    }
//                }
//            };
//        }
//
//        @Bean
//        public CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Arrays.asList("http://localhost:9090")); // Adjust to your frontend's origin
//            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//            configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//            configuration.setAllowCredentials(true); // If you need to send cookies
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);  // Apply to all paths
//            return source;
//        }
//    }


package com.restaurantmanagementsystem.shopper.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/register", "/css/**", "/js/**", "/images/**", "/about", "/contact", "/").permitAll()
                        .requestMatchers("/shopper/menu", "/shopper/menuItem/api/list").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(successHandler())
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        InMemoryUserDetailsManager inMemoryManager = new InMemoryUserDetailsManager(admin);

        return username -> {
            if ("admin".equals(username)) {
                return inMemoryManager.loadUserByUsername(username);
            }
            // Load shopper from the database (pseudo-code, replace with actual repository logic)
            return loadShopperFromDatabase(username);
        };
    }

    private UserDetails loadShopperFromDatabase(String username) {
        // Replace this with actual database call
        if ("shopper".equals(username)) {
            return User.builder()
                    .username("shopper")
                    .password(passwordEncoder().encode("shopper"))
                    .roles("SHOPPER")
                    .build();
        }
        throw new RuntimeException("User not found");
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                Set<String> roles = org.springframework.security.core.authority.AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                if (roles.contains("ROLE_ADMIN")) {
                    response.sendRedirect("/admin/menu");
                } else if (roles.contains("ROLE_SHOPPER")) {
                    response.sendRedirect("/shopper/menu");
                } else {
                    response.sendRedirect("/");
                }
            }
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:9090"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
