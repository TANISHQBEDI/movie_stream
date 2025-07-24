package com.backend.iffatv.controller.user;

import com.backend.iffatv.dto.AuthRequest;
import com.backend.iffatv.dto.AuthResponse;
import com.backend.iffatv.dto.RegisterRequest;
import com.backend.iffatv.model.user.Profile;
import com.backend.iffatv.model.user.Role;
import com.backend.iffatv.model.user.User;
import com.backend.iffatv.repository.user.ProfileRepository;
import com.backend.iffatv.repository.user.UserRepository;
import com.backend.iffatv.service.user.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("Email already registered"));
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(Role.ADMIN.USER))
                .build();

        Profile profile = Profile.builder()
                        .name(request.getName())
                .user(user)
                        .build()

                ;

        userRepository.save(user);
        profileRepository.save(profile);

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity
                    .status(401)
                    .body(new AuthResponse("Invalid email or password"));
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // safe now because authentication passed

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

