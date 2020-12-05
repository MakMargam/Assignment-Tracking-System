/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.dto;

import com.mycompany.ats.model.Role;

/**
 *
 * @author abhay
 */
public class RoleDetails {
    
    private Role role;
    
    public RoleDetails(Role role){
        this.role = role;
    }
    
        public int getId() {
        return role.getId();
    }

    public String getRoleName() {
        return role.getRoleName();
    }

    public String getRoleDesc() {
        return role.getRoleDesc();
    }

    public boolean isDeleted() {
        return role.isDeleted();
    }
    
}
