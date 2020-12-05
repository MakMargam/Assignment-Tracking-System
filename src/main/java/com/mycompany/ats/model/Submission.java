/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author abhay
 */
@Entity(name = "submission")
@Getter
@Setter
public class Submission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "submitted_on", columnDefinition = "DATE")
    private Date submittedOn;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;
    
    @Column(name ="attachment")
    private String attachment;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SubmittedBy", referencedColumnName = "id")
    private User submittedBy;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignmentId", referencedColumnName = "id")
    private Assignment assignmentId;

    public Submission() {
    }

    public Submission(int id) {
        this.id = id;
    }

    public Submission(User submittedBy, Assignment assignmentId) {
        this.submittedBy = submittedBy;
        this.assignmentId = assignmentId;
    }

    

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Date getSubmittedOn() {
//        return submittedOn;
//    }
//
//    public void setSubmittedOn(Date submittedOn) {
//        this.submittedOn = submittedOn;
//    }
//
//    public boolean getIsDeleted() {
//        return isDeleted;
//    }
//
//    public void setIsDeleted(boolean isDeleted) {
//        this.isDeleted = isDeleted;
//    }
//
//    public User getSubmittedBy() {
//        return SubmittedBy;
//    }
//
//    public void setSubmittedBy(User SubmittedBy) {
//        this.SubmittedBy = SubmittedBy;
//    }
//
//    public Assignment getAssignmentId() {
//        return assignmentId;
//    }
//
//    public void setAssignmentId(Assignment assignmentId) {
//        this.assignmentId = assignmentId;
//    }
}
