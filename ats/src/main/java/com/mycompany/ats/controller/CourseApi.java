/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;

import com.mycompany.ats.dto.CourseDetails;
import com.mycompany.ats.model.Course;
import com.mycompany.ats.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author abhay
 */
@RestController
@RequestMapping(value = "course")
public class CourseApi {
    
    @Autowired
    private CourseService courseService;
    
    /**
     * add course method
     * @param courseName
     * @return 
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = "application/json")
    public CourseDetails addCourse(@RequestParam(name = "coursename", required = true)String courseName){
        Course course=new Course(courseName);
        return new CourseDetails(courseService.addCourse(course));
    }
    
    /**
     * Update course method
     * @param id
     * @param courseName
     * @param isDeleted
     * @return 
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = "application/json")
    public CourseDetails updateCourse(
            @PathVariable(name = "id")Integer id,
            @RequestParam(name = "course-name", required = false)String courseName,
            @RequestParam(name = "deleted", required = false)Integer isDeleted
    ){
        Course course = courseService.fetch(id);
        if(course == null)
            return null;
        if(courseName != null)
            course.setCourseName(courseName);
        if(isDeleted != null)
            course.setDeleted(isDeleted == 0 ? true : false);
        return new CourseDetails(courseService.addCourse(course));
    }    
    
    /**
     * fetch all courses method
     * @return 
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<CourseDetails> fetchAll(){
        List<CourseDetails> courses = new ArrayList<>();
        for(Course c : courseService.allCourse())
        {
            courses.add(new CourseDetails(c));
        }
        return courses;
    }
    
    
    /**
     * fetch course method
     * @param id
     * @return 
     */
    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = "application/json")
    public CourseDetails fetch(@RequestParam(name = "id", required = true)int id)
    {
        return new CourseDetails(courseService.fetch(id));
    }
}
