/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.service;

import com.mycompany.ats.model.Submission;
import com.mycompany.ats.model.User;
import com.mycompany.ats.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 *
 * @author abhay
 */
@Service
public class SubmissionService {
    
    @Autowired
    private SubmissionRepository submissionRepo;
    
    public Submission addSubmission(Submission submission){
        return submissionRepo.save(submission);
    }
    
    public List<Submission> fetchAll(){
        List<Submission> submissions = new ArrayList<>();
        for(Submission s : submissionRepo.findAll()){
            submissions.add(s);
        }
        return submissions;
    }
    
    public List<Submission> fetchAllByUser(User user){
        List<Submission> submissions = new ArrayList<>();
        for(Submission s : submissionRepo.findAllBySubmittedBy(user)){
            submissions.add(s);
        }
        return submissions;
    }
    
    public Submission fetch(int x){
        return submissionRepo.findById(x).get();
    }
    
    public int getSubmissionCount() {
    	Iterable<Submission> values = submissionRepo.findAll();
    	if (values instanceof Collection<?>) {
    		  return ((Collection<?>)values).size();
    		}
    	return 0;
    }
}
