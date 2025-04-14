package rlatnfla.securitytest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rlatnfla.securitytest.dto.SignupRequest;
import rlatnfla.securitytest.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public Long signup(@RequestBody SignupRequest signupRequest) {
        return authService.createMember(signupRequest);
    }
}
