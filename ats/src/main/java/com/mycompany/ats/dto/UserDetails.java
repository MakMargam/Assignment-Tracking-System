/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.dto;

import com.mycompany.ats.model.User;

/**
 *
 * @author abhay
 */
public class UserDetails {
    
    private User user;
    
    public UserDetails(User user){
        this.user = user;
    }
    
    public int getId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getUserName();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getName() {
        return user.getName();
    }
    
    public String getPhoto(){
        return user.getPhoto();
    }

    public boolean isActive() {
        return user.isActive();
    }

    public boolean isDeleted() {
        return user.isDeleted();
    }
    
    public GroupDetails getGroupId() {
        return new GroupDetails(user.getGroupId());
    }
    
    public RoleDetails getRoleId() {
        return new RoleDetails(user.getRoleId());
    }
}
