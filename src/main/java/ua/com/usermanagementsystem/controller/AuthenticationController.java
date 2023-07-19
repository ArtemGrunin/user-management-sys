package ua.com.usermanagementsystem.controller;

import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.usermanagementsystem.dto.request.UserLoginRequestDto;
import ua.com.usermanagementsystem.exception.AuthenticationException;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.security.AuthenticationService;
import ua.com.usermanagementsystem.security.jwt.JwtTokenProvider;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginRequestDto userLoginDto)
            throws AuthenticationException {
        UserAccount userAccount = authenticationService.login(
                userLoginDto.getUsername(),
                userLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(userAccount.getUsername(),
                userAccount.getRole());
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
