/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.dto;

import com.mycompany.ats.model.Course;

/**
 *
 * @author abhay
 */
public class CourseDetails {
    
    private Course course;
    
    public CourseDetails(Course course){
        this.course = course;
    }

    public int getId() {
        return course.getId();
    }

    public String getCourseName() {
        return course.getCourseName();
    }

    public boolean isDeleted() {
        return course.isDeleted();
    }

}
