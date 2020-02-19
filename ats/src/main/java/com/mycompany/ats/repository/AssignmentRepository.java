/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.repository;

import com.mycompany.ats.model.Assignment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abhay
 */
@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Integer>{
    
    
    
}
