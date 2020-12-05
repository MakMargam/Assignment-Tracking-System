/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.service;

import com.mycompany.ats.model.Assignment;
import com.mycompany.ats.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 *
 * @author abhay
 */
@Service
public class AssignmentService {
    
    @Autowired
    private AssignmentRepository assignmentRepo;
    
    public Assignment addAssignment(Assignment assignment){
        return assignmentRepo.save(assignment);
    }
    
    public List<Assignment> fetchAll(){
        List<Assignment> assignments = new ArrayList<>();
        for(Assignment asmnt : assignmentRepo.findAll())
        {
            assignments.add(asmnt);
        }
        return assignments;
    }
    
    public Assignment fetch(int x)
    {
        return assignmentRepo.findById(x).get();
    }
    
    public int getAssignmentCount() {
    	Iterable<Assignment> values = assignmentRepo.findAll();
    	if (values instanceof Collection<?>) {
    		  return ((Collection<?>)values).size();
    		}
    	return 0;
    }
}
