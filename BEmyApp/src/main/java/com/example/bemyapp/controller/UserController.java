package com.example.bemyapp.controller;

import com.example.bemyapp.model.Response;
import com.example.bemyapp.model.TbUser;
import com.example.bemyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/login/{username}/{password}")
    private Response login(@PathVariable String username, @PathVariable String password){

        TbUser user = service.login(username, password);
        if (user!=null){
            return new Response("Login Success","200", user);
        }
        return new Response("Login Failed",null, null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/register/{username}/{password}/{age}")
    private Response register(@PathVariable String username, @PathVariable String password, @PathVariable String age){

        if(!service.isValidPassword(password)){
            return new Response("Password not valid",null,null);
        }
        if(Integer.parseInt(age)<18){
            return new Response("Min Age 18",null,null);
        }
        TbUser user = service.register(username, password, age);
        if (user!=null){
            return new Response("Register Success","200", user);
        }
        return new Response("Register Failed", null, null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/view")
    private List<TbUser> allData(){

        return service.viewAll();
    }
}
