package com.hexschool.keybr.controller;

import com.hexschool.keybr.model.RaceRecordDto;
import com.hexschool.keybr.service.FetchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiController {

    @Autowired
    FetchDataService ss;

    @GetMapping("/users")
    public List<RaceRecordDto> getPage() {
        return ss.getData();
    }
}
