/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.repository;

import com.mycompany.ats.model.Assignment;
import com.mycompany.ats.model.Submission;
import com.mycompany.ats.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abhay
 */
@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Integer>{
    
    List<Submission> findAllBySubmittedBy(User user);
    
    List<Submission> findAllBySubmittedByAndAssignmentId(User user, Assignment assignment);
    
}
