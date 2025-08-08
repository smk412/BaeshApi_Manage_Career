package com.example.Baesh.Controller;

import com.example.Baesh.DTO.AuthResponse;
import com.example.Baesh.DTO.LoginRequest;
import com.example.Baesh.DTO.SignUpRequset;
import com.example.Baesh.Entity.UserE;
import com.example.Baesh.JWT.JwtUtil;
import com.example.Baesh.Repository.UserRepository;
import com.example.Baesh.Service.UserSignUp;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserSignUp userSignUp;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UserE userE = userRepository.findByEmail(request.getUserId())
                    .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword()));
            String token = jwtUtil.generateToken(request.getUserId());

            return ResponseEntity.ok(new AuthResponse(userE.getId(),token));
        } catch (Exception e) {
            System.out.println("e: "+e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> singUp(@RequestBody SignUpRequset signUpRequset) {
        try {
            String res = userSignUp.SignUp(signUpRequset);

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SignUp failure");
        }
    }

    @PostMapping("/signUp/checkEmail")
    public  ResponseEntity<?> checkEmail(@RequestBody SignUpRequset signUpRequset){
        boolean exists = userRepository.findByEmail(signUpRequset.getEmail()).isPresent();
        System.out.println(exists);
        return ResponseEntity.ok(exists ? 1 : 0); // 1: 중복, 0: 사용 가능
    }

    // ✅ Google OAuth 로그인 성공 후 React에서 credential(id_token) 보내는 방식
    @PostMapping("/loginSuccess")
    public ResponseEntity<?> oauthLogin(@RequestBody Map<String, String> body) {
        String idToken = body.get("credential");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new JacksonFactory()
        )
                .setAudience(Collections.singletonList("376856651781-0rm00lsklk95gak2mh4h9bka6a47vi9l.apps.googleusercontent.com")) // 👈 수정 필요
                .build();

        try {
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("name");

                Optional<UserE> userOptional = userRepository.findByEmail(email);
                UserE user = userOptional.orElseGet(() -> {
                    UserE newUser = new UserE(
                            "google_" + UUID.randomUUID(),
                            name,
                            email,
                            "ROLE_USER"
                    );
                    return userRepository.save(newUser);
                });

                String token = jwtUtil.generateToken(user.getUserId());
                return ResponseEntity.ok(new AuthResponse(user.getId(),token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid ID token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token verification failed");
        }
    }
}
