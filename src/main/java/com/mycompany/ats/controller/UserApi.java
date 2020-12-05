/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;

import com.mycompany.ats.dto.UserDetails;
import com.mycompany.ats.model.User;
import com.mycompany.ats.service.GroupService;
import com.mycompany.ats.service.RoleService;
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
@RequestMapping(value = "user")
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private RoleService roleService;

    public static String uploadDirectory = System.getProperty("user.dir") + 
    		System.getProperty("file.separator")+ "data"+
    		System.getProperty("file.separator")+ "photos";

    /**
     *
     * Add User Method
     *
     * @param userName
     * @param password
     * @param name
     * @return
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = "application/json")
    public UserDetails adduser(
            @RequestParam(name = "user-name", required = true) String userName,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "name", required = false) String name) {
    	if(password ==null || password == "")
    		password= "Pass@123";
        User user = new User(userName, password);
        if (name == null) {
            user.setName("");
        } else {
            user.setName(name);
        }
        if(userService.fetchAll().size() > 0)
        {
        	user.setRoleId(roleService.fetch(3));
        }
        else
        {
        	user.setRoleId(roleService.fetch(1));
        }
        user.setGroupId(groupService.fetch(1));
        user.setActive(true);
        return new UserDetails(userService.newUser(user));
    }

    /**
     * Update User method
     *
     * @param id
     * @param userName
     * @param password
     * @param photo
     * @param name
     * @param role
     * @param group
     * @param isDel
     * @param isAct
     * @return
     */
    @RequestMapping(value = "/update",
            method = RequestMethod.POST,
            produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDetails updateUser(
            @RequestParam(name = "username") String userName,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "role", required = false) String role,
            @RequestParam(name = "group", required = false) String group,
            @RequestParam(name = "deleted", required = false) String isDel,
            @RequestParam(name = "active", required = false) String isAct,
            @RequestParam(name = "photo", required = false) MultipartFile photo)throws ParseException, IOException {
        User user = userService.fetchByUserName(userName);
        if (user == null) {
            return null;
        }
        if (password != null) {
            user.setPassword(password);
        }
        try {
            new File(uploadDirectory).mkdir();
            String fileName=photo.getOriginalFilename().replaceAll(" ","_");
            Path fileNameAndPath = Paths.get(uploadDirectory, fileName);
            Files.write(fileNameAndPath, photo.getBytes());
            user.setPhoto(fileName);
        } catch (Exception e) {
            System.out.println("photo Not Given");
        }
        if (name != null) {
            user.setName(name);
        }
        if (isDel != null) {
            if (isDel.equals("Yes")) {
                user.setDeleted(true);
            } else if (isDel.equals("No")) {
                user.setDeleted(false);
            }
        }
        if (isAct != null) {
            if (isAct.equals("Yes")) {
                user.setActive(true);
            } else if (isAct.equals("No")) {
                user.setActive(false);
            }
        }

        if (role != null) {
            if (role.equals("user")) {
                user.setRoleId(roleService.fetch(1));
            } else if (role.equals("admin")) {
                user.setRoleId(roleService.fetch(2));
            }
        }
        if (group != null) {
            user.setGroupId(groupService.fetchByGroupName(group));
        }
        return new UserDetails(userService.newUser(user));
    }

    /**
     * View all user method
     *
     * @return
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<UserDetails> allUsers() {
        ArrayList<UserDetails> users = new ArrayList<>();
        for (User u : userService.fetchAll()) {
            users.add(new UserDetails(u));
        }

        return users;
    }

    /**
     * Fetch user by Id method
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDetails fetch(@RequestParam(name = "id", required = true) int id) {
        return new UserDetails(userService.fetch(id));
    }

    @RequestMapping(value = "getuser",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDetails fetchByUserName(@RequestParam(name = "username", required = true) String userName) {
        return new UserDetails(userService.fetchByUserName(userName));
    }
    
    @RequestMapping(value = "currentuser",
            method = RequestMethod.GET,
            produces = "application/json")
    public UserDetails getCurrentUser(Authentication auth) {
        return new UserDetails(userService.fetchByUserName(auth.getName()));
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
            FileInputStream finput = new FileInputStream(uploadDirectory + System.getProperty("file.separator")+  fileName);
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
