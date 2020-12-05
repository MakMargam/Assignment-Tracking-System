/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author abhay
 */
@Entity(name = "assignments")
@Getter
@Setter
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "posted_on", columnDefinition = "DATE")
    private Date postedOn;
    
    @Column(name = "assignment_name")
    private String assignmentName;
    
    @Column(name = "assignment_desc", columnDefinition = "LONGTEXT")
    private String assignmentDesc;
    
    @Column(name = "deadline", columnDefinition = "DATE")
    private Date deadline;
    
    @Column(name = "start_time", columnDefinition = "DATE")
    private Date startTime;
    
    @Column(name ="attachment")
    private String attachment;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private Course courseId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postedBy", referencedColumnName = "id")
    private User postedBy;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postedFor", referencedColumnName = "id")
    private Group postedFor;
    
    @OneToMany(mappedBy = "assignmentId")
    private List<Submission> submissions;

    public Assignment() {
    }

    public Assignment(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Assignment(int id) {
        this.id = id;
    }

    

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Date getPostedOn() {
//        return postedOn;
//    }
//
//    public void setPostedOn(Date postedOn) {
//        this.postedOn = postedOn;
//    }
//
//    public Date getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(Date deadline) {
//        this.deadline = deadline;
//    }
//
//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
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
//    public Course getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Course courseId) {
//        this.courseId = courseId;
//    }
//
//    public User getPostedBy() {
//        return postedBy;
//    }
//
//    public void setPostedBy(User postedBy) {
//        this.postedBy = postedBy;
//    }
//
//    public Group getPostedFor() {
//        return postedFor;
//    }
//
//    public void setPostedFor(Group postedFor) {
//        this.postedFor = postedFor;
//    }
//
//    public List<Submission> getSubmissions() {
//        return submissions;
//    }
//
//    public void setSubmissions(List<Submission> submissions) {
//        this.submissions = submissions;
//    }
    
}
