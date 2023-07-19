package ua.com.usermanagementsystem.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.usermanagementsystem.exception.UserAccountNotFoundException;
import ua.com.usermanagementsystem.model.Role;
import ua.com.usermanagementsystem.model.RoleName;
import ua.com.usermanagementsystem.model.Status;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.service.RoleService;
import ua.com.usermanagementsystem.service.UserAccountService;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String ADMIN_FIRSTNAME = "Ivan";
    private static final String ADMIN_LASTNAME = "Ivanchuk";
    private static final String USER_FIRSTNAME = "Petro";
    private static final String USER_LASTNAME = "Petrenko";
    private static final String USER_LOGIN = "user";
    private static final String USER_PASSWORD = "user123";
    private final RoleService roleService;
    private final UserAccountService userAccountService;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void dataInit() {
        Role adminRole = roleService.getByName(RoleName.ADMIN);
        Role userRole = roleService.getByName(RoleName.USER);
        try {
            userAccountService.findByUsername(ADMIN_LOGIN);
            userAccountService.findByUsername(USER_LOGIN);
        } catch (UserAccountNotFoundException e) {
            UserAccount adminUser = new UserAccount();
            adminUser.setUsername(ADMIN_LOGIN);
            String encodedPassword = passwordEncoder.encode(ADMIN_PASSWORD);
            adminUser.setPassword(encodedPassword);
            adminUser.setFirstName(ADMIN_FIRSTNAME);
            adminUser.setLastName(ADMIN_LASTNAME);
            adminUser.setStatus(Status.ACTIVE);
            adminUser.setRole(roleService.getByName(RoleName.ADMIN));
            userAccountService.add(adminUser);
            UserAccount userUser = new UserAccount();
            userUser.setUsername(USER_LOGIN);
            encodedPassword = passwordEncoder.encode(USER_PASSWORD);
            userUser.setPassword(encodedPassword);
            userUser.setFirstName(USER_FIRSTNAME);
            userUser.setLastName(USER_LASTNAME);
            userUser.setStatus(Status.ACTIVE);
            userUser.setRole(roleService.getByName(RoleName.USER));
            userAccountService.add(userUser);
        }
    }
}
