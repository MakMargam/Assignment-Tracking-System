/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.service;

import com.mycompany.ats.model.Course;
import com.mycompany.ats.repository.CourseRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author abhay
 */
@Service
public class CourseService {
    
    @Autowired
    CourseRepository courseRepo;
    
    public Course addCourse(Course course){
        return courseRepo.save(course);
    }
    
    public List<Course> allCourse(){
        List<Course> courses = new ArrayList<>();
        for(Course c : courseRepo.findAll())
        {
            courses.add(c);
        }
        return courses;
    }
    
    public Course fetch(int x){
        return courseRepo.findById(x).get();
    }
    public Course fetchByCourseName(String x){
        return courseRepo.findBycourseName(x).get();
    }
}
