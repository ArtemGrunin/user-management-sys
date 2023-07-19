package ua.com.usermanagementsystem.security.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.usermanagementsystem.exception.AuthenticationException;
import ua.com.usermanagementsystem.model.Status;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.security.AuthenticationService;
import ua.com.usermanagementsystem.service.UserAccountService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount login(String username, String password) throws AuthenticationException {
        UserAccount userAccount = userAccountService.findByUsername(username);
        if (userAccount == null) {
            throw new AuthenticationException("Invalid credentials.");
        }
        if (!passwordEncoder.matches(password, userAccount.getPassword())) {
            throw new AuthenticationException("Invalid password.");
        }
        if (userAccount.getStatus().equals(Status.INACTIVE)) {
            throw new AuthenticationException("Status inactive.");
        }
        return userAccount;
    }
}
