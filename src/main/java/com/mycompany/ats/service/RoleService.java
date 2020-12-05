/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.service;

import com.mycompany.ats.model.Role;
import com.mycompany.ats.repository.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author abhay
 */
@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepo;
    
    public Role createRole(Role role){
        return roleRepo.save(role);
    }
    
    public List<Role> allRole(){
        List<Role> li =new ArrayList<>(); 
        for(Role r: roleRepo.findAll())
        {
            li.add(r);
        }
        return li;
    }
    public Role fetch(int x){
        return roleRepo.findById(x);
    }
    public Role deleteRole(String roleName){
        Role role=roleRepo.findByRoleNameAndIsDeleted(roleName, Boolean.FALSE);
        if(role==null)
        {
            return role;
        }
        role.setDeleted(true);
        return roleRepo.save(role);
    }
}
