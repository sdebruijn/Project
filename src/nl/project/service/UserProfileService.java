package nl.project.service;
//package com.websystique.springsecurity.service;
 
import java.util.List;
 
import nl.project.user.UserProfile;
 
public interface UserProfileService {
 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}
