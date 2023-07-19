package ua.com.usermanagementsystem.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import ua.com.usermanagementsystem.model.Status;
import ua.com.usermanagementsystem.model.UserAccount;

public interface UserAccountService {
    UserAccount add(UserAccount userAccount);

    UserAccount get(Long id);

    List<UserAccount> findAll(PageRequest pageRequest);

    void delete(Long id);

    UserAccount update(UserAccount userAccount);

    UserAccount findByUsername(String username);

    UserAccount changeStatus(Long id, Status newStatus);
}
