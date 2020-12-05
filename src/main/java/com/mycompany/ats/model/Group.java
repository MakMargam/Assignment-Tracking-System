/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author abhay
 */
@Entity(name = "user_group")
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "group_name", nullable = false, unique = true)
    private String groupName;
    
    @Column(name = "group_description", columnDefinition = "TEXT")
    private String groupDesc;
    
    @Column(name = "is_active")
    private boolean isActive;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;
    
    
    
    @OneToMany(mappedBy = "groupId")
    private List<User> users;
    
    @OneToMany(mappedBy = "postedFor")
    private List<Assignment> assignments;

    public Group(int id) {
        this.id = id;
    }

    public Group() {
    }

    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getGroupName() {
//        return groupName;
//    }
//
//    public void setGroupName(String groupName) {
//        this.groupName = groupName;
//    }
//
//    public String getGroupDesc() {
//        return groupDesc;
//    }
//
//    public void setGroupDesc(String groupDesc) {
//        this.groupDesc = groupDesc;
//    }
//
//    public boolean getIsActive() {
//        return isActive;
//    }
//
//    public void setIsActive(boolean isActive) {
//        this.isActive = isActive;
//    }
//
//    public boolean getIsDeleted() {
//        return isDeleted;
//    }
//
//    public void setIsDeleted(boolean isDeleted) {
//        this.isDeleted = isDeleted;
//    }
//
//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
//
//    public List<Assignment> getAssignments() {
//        return assignments;
//    }
//
//    public void setAssignments(List<Assignment> assignments) {
//        this.assignments = assignments;
//    }
}
