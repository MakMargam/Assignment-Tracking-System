/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;

import com.mycompany.ats.dto.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author abhay
 */
@Controller
public class Views {

    @Autowired
    private UserApi userApi;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "name", required = false) String name) {

        if (name == null) {
            name = "";
        }
        try {
            UserDetails ud = userApi.adduser(username, password, name);
        } catch (Exception e) {
            System.out.println("User Already exists");
        }

        return "login";
    }

    @RequestMapping(value = "/")
    public String home(Authentication auth, Model model) {
        String username = auth.getName();
        UserDetails user = userApi.fetchByUserName(username);
        model.addAttribute("username", user.getUserName());
        model.addAttribute("name", user.getName());
        model.addAttribute("status", user.getRoleId().getRoleName());
        if(user.getRoleId().getRoleName().equals("admin"))
                return "home";
            else
                return "user";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            UserDetails user = userApi.fetchByUserName(username);
            model.addAttribute("username", user.getUserName());
            model.addAttribute("name", user.getName());
            model.addAttribute("status", user.getRoleId().getRoleName());
            if(user.getRoleId().getRoleName().equals("admin"))
                return "home";
            else
                return "user";
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            UserDetails user = userApi.fetchByUserName(username);
            model.addAttribute("username", user.getUserName());
            model.addAttribute("name", user.getName());
            model.addAttribute("status", user.getRoleId().getRoleName());
            if(user.getRoleId().getRoleName().equals("admin"))
                return "home";
            else
                return "user";
        }
        return "register";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String adminUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            UserDetails user = userApi.fetchByUserName(username);
            model.addAttribute("username", user.getUserName());
            model.addAttribute("name", user.getName());
            model.addAttribute("status", user.getRoleId().getRoleName());
            return "user";
            }
        return "register";
    }
}
