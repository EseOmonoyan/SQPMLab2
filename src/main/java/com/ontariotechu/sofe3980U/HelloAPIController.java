package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAPIController {

    @GetMapping("/helloAPI")
    public String helloAPI(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/emailAPI")
    public APIResult emailAPI(
            @RequestParam(name = "fname", required = false, defaultValue = "") String fname,
            @RequestParam(name = "lname", required = false, defaultValue = "") String lname) {
        String fullName = (fname + " " + lname).trim();
        if (fullName.isEmpty()) fullName = "Unknown";
        String email = (fname.isEmpty() ? "unknown" : fname.toLowerCase())
                + (lname.isEmpty() ? "" : "." + lname.toLowerCase())
                + "@example.com";
        return new APIResult(fullName, email);
    }
}
