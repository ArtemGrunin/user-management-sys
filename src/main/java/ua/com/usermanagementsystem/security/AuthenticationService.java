package ua.com.usermanagementsystem.security;

import ua.com.usermanagementsystem.exception.AuthenticationException;
import ua.com.usermanagementsystem.model.UserAccount;

public interface AuthenticationService {
    UserAccount login(String username, String password) throws AuthenticationException;
}
