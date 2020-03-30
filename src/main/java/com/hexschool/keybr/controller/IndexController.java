package com.hexschool.keybr.controller;

import com.hexschool.keybr.model.RaceRecordDto;
import com.hexschool.keybr.service.FetchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @GetMapping("/")
    public String getPage() {
        return "hello";
    }
}
