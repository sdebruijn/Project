package nl.project.dao;
//package com.websystique.springsecurity.dao;
 
import java.util.List;
 
import nl.project.user.UserProfile;
 
public interface UserProfileDao {
 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}
