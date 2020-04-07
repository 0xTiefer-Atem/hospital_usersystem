package org.usersystem.demo.controller;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class HtmlControl {

    @RequestMapping("/departments")
    public String getLoginHtml(){
        return "html/departments";
    }

    @RequestMapping("/index")
    public String returnIndex(){
        return "html/index";
    }

    @RequestMapping("/appointment")
    public String returnTest(){
        return "html/appointment";
    }

    @RequestMapping("/services")
    public String services(){
        return "html/services";
    }

    @RequestMapping("/doctors")
    public String returnTestAdd(){
        return "html/doctors";
    }
}
