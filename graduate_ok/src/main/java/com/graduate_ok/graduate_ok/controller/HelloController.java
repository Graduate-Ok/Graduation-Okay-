package com.graduate_ok.graduate_ok.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

    @RestController
    public class HelloController {
        @GetMapping("/hello")
        public List<String> Hello(){
            return Arrays.asList("안녕! Hello! Nihao!");
        }
    }

