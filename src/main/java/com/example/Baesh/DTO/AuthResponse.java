package com.example.Baesh.DTO;

import lombok.Data;

@Data
public class AuthResponse {
    public Long id;
    private String userToken;

    public AuthResponse(Long id,String userToken){
        this.id = id;
        this.userToken = userToken;
    }
}
