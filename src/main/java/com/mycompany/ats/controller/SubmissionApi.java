/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;

import com.mycompany.ats.dto.SubmissionDetails;
import com.mycompany.ats.model.Assignment;
import com.mycompany.ats.model.Submission;
import com.mycompany.ats.model.User;
import com.mycompany.ats.repository.SubmissionRepository;
import com.mycompany.ats.service.AssignmentService;
import com.mycompany.ats.service.SubmissionService;
import com.mycompany.ats.service.UserService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author abhay
 */
@RestController
@RequestMapping(value = "submission")
public class SubmissionApi {
    
    @Autowired
    private SubmissionService submissionService;
    
    @Autowired
    private SubmissionRepository submissionRepo;
    
    @Autowired
    private AssignmentService assignmentService;
    
    @Autowired
    private UserService userService;
    
    public static String uploadDirectory = System.getProperty("user.dir") + "\\data\\submissions";
    
    /**
     * Add Submission method
     * 
     * @param auth
     * @param file
     * @param assignment
     * @return 
     * @throws java.text.ParseException 
     * @throws java.io.IOException 
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SubmissionDetails addSubmission(Authentication auth,
            @RequestParam(name = "files", required = false) MultipartFile file,
            @RequestParam(name = "assignment", required = true)int assignment)throws ParseException, IOException {
        Submission submission = new Submission(userService.fetchByUserName(auth.getName()), assignmentService.fetch(assignment));
        try {
            new File(uploadDirectory).mkdir();
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes()); 
            submission.setAttachment(file.getOriginalFilename());
        } catch (Exception e) {
            System.out.println("No submission uploaded");
        }
        submission.setSubmittedOn(new java.util.Date());
        return new SubmissionDetails(submissionService.addSubmission(submission));
    }
           
    
    /**
     * Fetch all submissions
     * @return 
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<SubmissionDetails> fetchAll(){
        List<SubmissionDetails> submissions = new ArrayList<>();
        for(Submission s : submissionService.fetchAll()){
            submissions.add(new SubmissionDetails(s));
        }
        return submissions;
    }
    
    
    /**
     * Fetch all submissions
     * @return 
     */
    @RequestMapping(value = "userall",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<SubmissionDetails> fetchByUser(Authentication auth){
        List<SubmissionDetails> submissions = new ArrayList<>();
        User user = userService.fetchByUserName(auth.getName());
        for(Submission s : submissionService.fetchAllByUser(user)){
            submissions.add(new SubmissionDetails(s));
        }
        return submissions;
    }
    
    @RequestMapping(value = "defaulter",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<SubmissionDetails> fetchByPostedByAndAssignmentId(Authentication auth,
             @RequestParam(name = "assignment", required = true)Integer id){
        List<SubmissionDetails> submissions = new ArrayList<>();
        User user = userService.fetchByUserName(auth.getName());
        Assignment assignment = assignmentService. fetch(id);
        for(Submission s : submissionRepo.findAllBySubmittedByAndAssignmentId(user, assignment)){
            submissions.add(new SubmissionDetails(s));
        }
        return submissions;
    }
    
    
    /**
     * Fetch one submission method
     * @param submissionId
     * @return 
     */
    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = "application/json")
    public SubmissionDetails fetch(@RequestParam(name = "id", required = true)int submissionId){
        return new SubmissionDetails(submissionService.fetch(submissionId));
    }
    
    
    @RequestMapping(value = "viewfile/{fileName}")
    @ResponseBody
    public void downloadAttachment(HttpServletResponse response,
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
