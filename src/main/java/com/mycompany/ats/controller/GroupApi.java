/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ats.controller;
import com.mycompany.ats.dto.GroupDetails;
import com.mycompany.ats.model.Group;
import com.mycompany.ats.service.GroupService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abhay
 */
@RestController
@RequestMapping(value = "group")
public class GroupApi {
    
   
    @Autowired
    private GroupService groupService;
    
    
    /**
     * Add Group method
     * @param groupName
     * @param groupDesc
     * @return 
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = "application/json")
    public GroupDetails addGroup(
            @RequestParam(name = "groupname", required = true) String groupName,
            @RequestParam(name = "groupdesc", required = false, defaultValue = "Group Description") String groupDesc)
    {
        Group group = new Group(groupName, groupDesc);
        return new GroupDetails(groupService.addGroup(group));
    }
    
    /**
     * Update group method
     * @param id
     * @param groupName
     * @param groupDesc
     * @return 
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = "application/json")
    public GroupDetails UpdateGroup(
            @PathVariable(name = "id") Integer id,
            @RequestParam(name = "group-name", required = false) String groupName,
            @RequestParam(name = "group-desc", required = false) String groupDesc){
        Group group = groupService.fetch(id);
        if(group == null)
            return null;
        if(groupName!=null)
            group.setGroupName(groupName);
        if(groupDesc!=null)
            group.setGroupDesc(groupDesc);
        return new GroupDetails(groupService.addGroup(group));
    }
    
    
    /**
     * fetch all groups mehod
     * @return 
     */
    @RequestMapping(value = "all",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<GroupDetails> fetchAll(){
        List<GroupDetails> li=new ArrayList<>();
        for(Group g: groupService.fetchAll())
        {
            li.add(new GroupDetails(g));
        }
        return li;
    }
    
    /**
     * fetch group method
     * @param id
     * @return 
     */
    @RequestMapping(value = "get",
            method = RequestMethod.GET,
            produces = "application/json")
    public GroupDetails fetch(@RequestParam(name = "id", required = true)int id)
    {
        return new GroupDetails(groupService.fetch(id));
    }
    
    /**
     * delete group method
     * @param id
     * @return 
     */
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public boolean delete(@PathVariable(name = "id", required = true) int id){
        return groupService.deleteGroup(id);
    }
}
