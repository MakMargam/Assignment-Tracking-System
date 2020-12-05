/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.repository;

import com.mycompany.ats.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abhay
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    
    Optional<User> findByuserName(String username);


}
