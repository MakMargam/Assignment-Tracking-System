/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.service;

import com.mycompany.ats.model.Group;
import com.mycompany.ats.repository.GroupRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author abhay
 */
@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepo;
    
    public Group addGroup(Group group){
        return groupRepo.save(group);
    } 
    
    public List<Group> fetchAll(){
        List<Group> li=new ArrayList<>();
        for(Group g :groupRepo.findAll())
        {
            li.add(g);
        }
        return li;
    }
    
    public Group fetch(int x){
        return groupRepo.findById(x).get();
    }
    
     public Group fetchByGroupName(String x){
        return groupRepo.findBygroupName(x).get();
    }
     
    public Boolean deleteGroup(Integer id){
        groupRepo.deleteById(id);
        return !groupRepo.existsById(id);
    }
}
