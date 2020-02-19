/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;

import com.mycompany.ats.dto.AssignmentDetails;
import com.mycompany.ats.model.Assignment;
import com.mycompany.ats.service.AssignmentService;
import com.mycompany.ats.service.CourseService;
import com.mycompany.ats.service.GroupService;
import com.mycompany.ats.service.UserService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author abhay
 */
@RestController
@RequestMapping(value = "assignment")
public class AssignmentApi {

    
    public static String uploadDirectory = System.getProperty("user.dir") + "\\uploads";

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Add assignment method
     *
     * @param assmntName
     * @param user
     * @param assignmentDesc
     * @param course
     * @param file
     * @param deadline
     * @param group
     * @param start
     * @return
     * @throws java.text.ParseException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AssignmentDetails addAssignment(@RequestParam(name = "assignmentname", required = true) String assmntName,
            @RequestParam(name = "postedby", required = true) String user,
            @RequestParam(name = "description", required = false, defaultValue = "No Description") String assignmentDesc,
            @RequestParam(name = "course", required = true) String course,
            @RequestParam(name = "deadline", required = false) String deadline,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "postedfor", required = true) String group,
            @RequestParam(name = "files", required = false) MultipartFile file) throws ParseException, IOException {
        Assignment assignment = new Assignment(assmntName);
        try {
            new File(uploadDirectory).mkdir();
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            assignment.setAttachment(file.getOriginalFilename());
        } catch (Exception e) {

        }
        assignment.setPostedFor(groupService.fetchByGroupName(group));
        assignment.setPostedBy(userService.fetchByUserName(user));
        assignment.setCourseId(courseService.fetchByCourseName(course));
        assignment.setAssignmentDesc(assignmentDesc);
        if (deadline != null) {
            assignment.setDeadline(formatter.parse(deadline));
        }
        if (start != null) {
            assignment.setStartTime(formatter.parse(start));
        }
        assignment.setPostedOn(new java.util.Date());

        return new AssignmentDetails(assignmentService.addAssignment(assignment));

    }

    /**
     * Update assignment method
     *
     * @param id
     * @param assmntName
     * @param assignmentDesc
     * @param user
     * @param course
     * @param postedOn
     * @param deadline
     * @param start
     * @param group
     * @param isDeleted
     * @param file
     * @return
     * @throws ParseException
     * @throws java.io.IOException
     * @throws java.nio.file.AccessDeniedException
     */
    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AssignmentDetails updateAssignment(@RequestParam(name = "id") Integer id,
            @RequestParam(name = "assignmentname", required = false) String assmntName,
            @RequestParam(name = "description", required = false) String assignmentDesc,
            @RequestParam(name = "postedby", required = false) String user,
            @RequestParam(name = "course", required = false) String course,
            @RequestParam(name = "postedon", required = false) String postedOn,
            @RequestParam(name = "deadline", required = false) String deadline,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "postedfor", required = false) String group,
            @RequestParam(name = "deleted", required = false) Integer isDeleted,
            @RequestParam(name = "files5", required = false) MultipartFile file) throws ParseException, IOException, java.nio.file.AccessDeniedException {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Assignment assignment = assignmentService.fetch(id);
        if (assignment == null) {
            return null;
        }
        try {
            new File(uploadDirectory).mkdir();
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            assignment.setAttachment(file.getOriginalFilename());
        } catch (Exception e) {
            System.out.println("File Not Given");
        }
        if (assmntName != null) {
            assignment.setAssignmentName(assmntName);
        }
        
        if(assignmentDesc != null && assignmentDesc != ""){
            assignment.setAssignmentDesc(assignmentDesc);
        }
        
        if (user != null) {
            assignment.setPostedBy(userService.fetchByUserName(user));
        }
        if (course != null) {
            assignment.setCourseId(courseService.fetchByCourseName(course));
        }
        if (postedOn != null) {
            assignment.setPostedOn(formatter.parse(postedOn));
        }
        if (deadline != null) {
            assignment.setDeadline(formatter.parse(deadline));
        }
        if (start != null) {
            assignment.setStartTime(formatter.parse(start));
        }
        if (group != null) {
            assignment.setPostedFor(groupService.fetchByGroupName(group));
        }
        if (isDeleted != null) {
            assignment.setDeleted(isDeleted == 0 ? false : true);
        }
        return new AssignmentDetails(assignmentService.addAssignment(assignment));
    }

    /**
     * Fetch all assignment methods
     *
     * @return
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<AssignmentDetails> fetchAll() {
        List<AssignmentDetails> assignments = new ArrayList<>();
        for (Assignment a : assignmentService.fetchAll()) {
            assignments.add(new AssignmentDetails(a));
        }
        return assignments;
    }
    
    
    /**
     * Fetch assignment method
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = "application/json")
    public AssignmentDetails fetch(@RequestParam(name = "id", required = true) int id) {
        return new AssignmentDetails(assignmentService.fetch(id));
    }

    @RequestMapping(value = "viewfile/{fileName}")
    @ResponseBody
    public void downloadAssigment(HttpServletResponse response,
            HttpServletRequest request,
            @PathVariable("fileName") String fileName
    ) {
        
        HttpHeaders header = new HttpHeaders();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName.replace(" ", "_"));
        try {
            BufferedOutputStream br = new BufferedOutputStream(response.getOutputStream());
            FileInputStream finput = new FileInputStream(uploadDirectory + "\\" + fileName);
            byte[] buff = new byte[1024];
            int length;
            while ((length = finput.read(buff)) > 0) {
                br.write(buff, 0, length);
            }
            br.close();
            response.flushBuffer();

        } catch (Exception e) {
            System.out.println("Invalid file name");
        }

    }

}
