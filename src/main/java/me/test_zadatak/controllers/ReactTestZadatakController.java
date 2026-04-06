package me.test_zadatak.controllers;

import me.test_zadatak.services.TestZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ReactTestZadatakController {

    @Autowired
    TestZadatakService service;

    @PostMapping("save")
    public boolean newCandidate(){
        return false;
    }

}
