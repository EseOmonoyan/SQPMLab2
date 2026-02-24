package com.ontariotechu.sofe3980U;

public class APIResult {
    private String name;
    private String email;

    public APIResult(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
}
