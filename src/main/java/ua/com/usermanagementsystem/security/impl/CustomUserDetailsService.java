package ua.com.usermanagementsystem.security.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.usermanagementsystem.exception.UserAccountNotFoundException;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.service.UserAccountService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount;
        try {
            userAccount = userService.findByUsername(username);
        } catch (UserAccountNotFoundException e) {
            throw new UsernameNotFoundException("User: " + username + " not found");
        }
        UserBuilder builder = withUsername(username);
        builder.password(userAccount.getPassword());
        builder.roles(userAccount.getRole().getName().name());
        return builder.build();
    }
}
