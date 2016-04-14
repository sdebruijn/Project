package nl.project.dao;
//package com.websystique.springsecurity.dao;
 
import nl.project.user.User;
 
public interface UserDao {
 
    void save(User user);
     
    User findById(int id);
     
    User findBySSO(String sso);
     
}
