/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.dto;

import com.mycompany.ats.model.Assignment;
import java.util.Date;

/**
 *
 * @author abhay
 */
public class AssignmentDetails {
    
    
    private Assignment assignment;
    
    public AssignmentDetails(Assignment assignment){
        this.assignment = assignment;
    }
    
    public int getId() {
        return assignment.getId();
    }
    
    public String getAssignmentDesc(){
        return assignment.getAssignmentDesc();
    }
    
    public Date getPostedOn() {
        return assignment.getPostedOn();
    }

    public Date getDeadline() {
        return assignment.getDeadline();
    }

    public Date getStartTime() {
        return assignment.getStartTime();
    }

    public boolean issDeleted() {
        return assignment.isDeleted();
    }
    
    public String getAssignmentName() {
        return assignment.getAssignmentName();
    }
    
    public CourseDetails getCourseId() {
        return new CourseDetails(assignment.getCourseId());
    }

    public UserDetails getPostedBy() {
        return new UserDetails(assignment.getPostedBy());
    }

    public GroupDetails getPostedFor() {
        return new GroupDetails(assignment.getPostedFor());
    }
    public String getAttachment()
    {
        return assignment.getAttachment();
    }
}
