/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.dto;

import com.mycompany.ats.model.Submission;
import java.util.Date;

/**
 *
 * @author abhay
 */
public class SubmissionDetails {
    
    private Submission submission;
    
    public SubmissionDetails(Submission submission){
        this.submission = submission;
    }
    
    
    public int getId() {
        return submission.getId();
    }

    public Date getSubmittedOn() {
        return submission.getSubmittedOn();
    }

    public boolean isDeleted() {
        return submission.isDeleted();
    }

    public UserDetails getSubmittedBy() {
        return new UserDetails(submission.getSubmittedBy());
    }
    
    public String getAttachment(){
        return submission.getAttachment();
    }
    
    public AssignmentDetails getAssignmentId() {
        return new AssignmentDetails(submission.getAssignmentId());
    }

}
