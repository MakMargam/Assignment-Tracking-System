/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.security;

import com.mycompany.ats.model.User;
import com.mycompany.ats.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author abhay
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> user = userRepo.findByuserName(username);
        
        if(user==null){
            throw new UsernameNotFoundException("User does not exist: " + username);
        }
        
        return new MyUserDetails(user.get());
    }
    
}