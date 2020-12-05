/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author abhay
 */
@Entity(name = "user")
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "user_name", nullable = false, unique = true)
    @Size(min = 3)
    private String userName;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "name")
    @Size(max = 128)
    private String name;
    
    @Column(name = "is_active")
    private boolean isActive;
    
    @Column(name = "photo")
    private String photo;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId", referencedColumnName = "id")
    private Group groupId;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private Role roleId;
    
    @OneToMany(mappedBy = "postedBy")
    private List<Assignment> assignments;
    
    @OneToMany(mappedBy = "submittedBy")
    private List<Submission> submissions;

    public User() {
    }
   
    public User(int id) {
        this.id = id;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
  
}
