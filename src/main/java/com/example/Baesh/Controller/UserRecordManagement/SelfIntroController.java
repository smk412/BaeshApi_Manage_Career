package com.example.Baesh.Controller.UserRecordManagement;

import com.example.Baesh.DTO.UserRecordManagement.SelfIntroDTO;
import com.example.Baesh.JWT.JwtUtil;
import com.example.Baesh.Service.UserRecordManagement.SelfIntroService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SelfIntroController {

    public final SelfIntroService selfIntroService;
    @GetMapping("/SelfIntroList")
    public List<SelfIntroDTO> getSelfIntroList(){
        return selfIntroService.SelfIntroList();
    }

    @PostMapping("/AISelfIntroFeedback")
    public SelfIntroDTO AISelfIntroFeedBack(@RequestBody SelfIntroDTO res){
        return selfIntroService.AISelfIntroFeedback(res);
    }



}
