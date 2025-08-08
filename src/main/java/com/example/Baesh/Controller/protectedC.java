package com.example.Baesh.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1")
public class protectedC {

    @GetMapping("/protected")
    public ResponseEntity<?> protectedRes(){
        return ResponseEntity.ok("토큰 인증 확인");
    }
}
