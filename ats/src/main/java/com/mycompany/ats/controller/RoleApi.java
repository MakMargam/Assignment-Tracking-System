/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;

import com.mycompany.ats.dto.RoleDetails;
import com.mycompany.ats.model.Role;
import com.mycompany.ats.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abhay
 */
@RestController
@RequestMapping(value = "role")
public class RoleApi {
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = "application/json")
    public RoleDetails newRole(@RequestParam(name = "role-name", required = true)String roleName,
                        @RequestParam(name = "role-desc", required = false, defaultValue = "New Role")String roleDesc
                        ){

        Role role=new Role(roleName, roleDesc);
        return new RoleDetails(roleService.createRole(role));
    }
    
    @RequestMapping(value = "/all",
            method=RequestMethod.GET,
            produces = "application/json")
    public List<RoleDetails> allRole(){
        
        List<RoleDetails> li=new ArrayList<>();
        for(Role r: roleService.allRole())
            li.add(new RoleDetails(r));
        return li;
    }
    
    @RequestMapping(value = "/delete",
            method=RequestMethod.GET,
            produces = "application/json")
    public RoleDetails deleteRole(@RequestParam(name = "role-name", required = true)String roleName){
        
        Role role=roleService.deleteRole(roleName);
        
        return new RoleDetails(role);
    }
    
    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = "application/json")
    public RoleDetails fetch(@RequestParam(name = "id", required = true)int id)
    {
        return new RoleDetails(roleService.fetch(id));
    }
}
