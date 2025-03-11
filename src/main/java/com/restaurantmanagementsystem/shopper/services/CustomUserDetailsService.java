//package com.restaurantmanagementsystem.shopper.services;
//
//import com.restaurantmanagementsystem.shopper.models.User;
//import com.restaurantmanagementsystem.shopper.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        // Create a UserDetails object from your User entity
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(), // The password should be BCrypt-encoded
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())) // Convert roles to authorities
//        );
//    }
//}