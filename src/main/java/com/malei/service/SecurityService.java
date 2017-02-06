package com.malei.service;



public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username);
}