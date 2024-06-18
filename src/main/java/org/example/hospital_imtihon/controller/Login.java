package org.example.hospital_imtihon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login {
    @GetMapping
    public String sentToLoginPage(){
        return "login";
    }
}
