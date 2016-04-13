package nl.project.service;
//package com.websystique.springsecurity.service;
 
import nl.project.user.User;
 
public interface UserService {
 
    void save(User user);
     
    User findById(int id);
     
    User findBySso(String sso);
     
}
