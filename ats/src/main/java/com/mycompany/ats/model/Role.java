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
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author abhay
 */
@Entity(name = "role")
@Getter
@Setter
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "role_name", unique = true, nullable = false)
    @Size(min = 3)
    private String roleName;
    
    @Column(name = "role_desc")
    private String roleDesc;
    
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "roleId")
    private List<User> users;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(String roleName, String roleDesc) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

}
