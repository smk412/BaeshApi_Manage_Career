package com.example.Baesh.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequset {
    private String userId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String location;
    private String intro;
    private String role;
}
