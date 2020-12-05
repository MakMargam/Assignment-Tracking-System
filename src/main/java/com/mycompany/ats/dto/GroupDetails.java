/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.dto;

import com.mycompany.ats.model.Group;

/**
 *
 * @author abhay
 */
public class GroupDetails {
    
    private Group group;
    
    public GroupDetails(Group group){
        this.group = group;
    }
    
       public int getId() {
        return group.getId();
    }

    public String getGroupName() {
        return group.getGroupName();
    }

    public String getGroupDesc() {
        return group.getGroupDesc();
    }

    public boolean isActive() {
        return group.isActive();
    }

    public boolean isDeleted() {
        return group.isDeleted();
    }

}
