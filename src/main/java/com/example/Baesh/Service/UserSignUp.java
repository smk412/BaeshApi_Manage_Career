package com.example.Baesh.Service;

import com.example.Baesh.DTO.SignUpRequset;
import com.example.Baesh.Entity.UserE;
import com.example.Baesh.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class UserSignUp {

    private final UserRepository userRepository;
    private final LocalLMMService localLMMService;
    private final UserTagService userTagService;

    public String SignUp(SignUpRequset signUpRequset) {
        try {
            // 1. 이메일 중복 체크
            boolean exists = userRepository.findByEmail(signUpRequset.getEmail()).isPresent();
            if (exists) {
                throw new RuntimeException("이미 존재하는 이메일입니다.");
            }
            // 2. 비밀번호 암호화
            String encodedPassword = new BCryptPasswordEncoder().encode(signUpRequset.getPassword());
            // 3. 유저 생성
            UserE user = new UserE(
                    signUpRequset.getUserId(),
                    encodedPassword,
                    signUpRequset.getName(),
                    signUpRequset.getEmail(),
                    signUpRequset.getPhoneNumber(),
                    signUpRequset.getLocation(),
                    signUpRequset.getIntro(),
                    signUpRequset.getRole()
            );
            userRepository.save(user);

            List<String> tags = localLMMService.extractTag(user.getIntro());
            userTagService.addTagsToUser(user.getUserId(),tags);

            // 4. 성공 시 반환
            return "true";
        } catch (Exception e) {
            System.out.println("회원가입 중 오류: " + e.getMessage());
            throw new RuntimeException("회원가입 실패");
        }
    }
}
