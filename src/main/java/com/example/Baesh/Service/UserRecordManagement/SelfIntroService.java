package com.example.Baesh.Service.UserRecordManagement;

import com.example.Baesh.DTO.UserRecordManagement.SelfIntroDTO;
import com.example.Baesh.Entity.UserE;
import com.example.Baesh.Entity.UserRecordManagement.SelfIntroEntity;
import com.example.Baesh.JWT.JwtUtil;
import com.example.Baesh.Repository.SelfIntroRepository;
import com.example.Baesh.Repository.UserRepository;
import com.example.Baesh.Service.LocalLMMService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelfIntroService {

    private final SelfIntroRepository selfIntroRepository;
    private final UserRepository userRepository;
    private final LocalLMMService localLMMService;
    private final JwtUtil jwtUtil;

    public List<SelfIntroDTO> SelfIntroList(){
        try{
            UserE userE = userRepository.findByUserId(jwtUtil.getUserName()).orElseThrow(
                    ()-> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
            );

            return selfIntroRepository.findByUserId(userE.getId())
                    .stream()
                    .map(SelfIntroDTO::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SelfIntroDTO AISelfIntroFeedback(SelfIntroDTO res){
        try {
            UserE userE = userRepository.findByUserId(jwtUtil.getUserName()).orElseThrow(
                    ()-> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
            );

            List<String> AiFeedback = localLMMService.AiFeedBack(res.getSubject(), res.getContent());

            SelfIntroEntity selfIntroEntity =new SelfIntroEntity(
                    userE.getId(),
                    res.getSubject(),
                    AiFeedback.getLast(),
                    AiFeedback.getFirst()
            );

            selfIntroRepository.save(selfIntroEntity);

            res.setFeedback(AiFeedback.getFirst());
            res.setContent(AiFeedback.getLast());

            return res;
        } catch (Exception e) {
            throw new RuntimeException("err: ",e);
        }
    }
}
