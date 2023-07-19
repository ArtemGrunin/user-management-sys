package ua.com.usermanagementsystem.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.usermanagementsystem.exception.UserAccountNotFoundException;
import ua.com.usermanagementsystem.model.Status;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.repository.UserAccountRepository;
import ua.com.usermanagementsystem.service.UserAccountService;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount add(UserAccount userAccount) {
        userAccount.setStatus(Status.ACTIVE);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount get(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(()
                        -> new RuntimeException("UserAccount with id " + id + " not found"));
    }

    @Override
    public List<UserAccount> findAll(PageRequest pageRequest) {
        return userAccountRepository.findAll(pageRequest).toList();
    }

    @Override
    public void delete(Long id) {
        if (!userAccountRepository.existsById(id)) {
            throw new UserAccountNotFoundException("User with id: " + id + " not found");
        }
        userAccountRepository.deleteById(id);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        if (!userAccountRepository.existsById(userAccount.getId())) {
            throw new UserAccountNotFoundException("User with id: "
                    + userAccount.getId() + " not found");
        }
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount findByUsername(String name) {
        return userAccountRepository.findByUsername(name)
                .orElseThrow(() -> new UserAccountNotFoundException("User with username: "
                        + name + " not found"));
    }

    @Override
    public UserAccount changeStatus(Long id, Status newStatus) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(()
                        -> new RuntimeException("UserAccount with id " + id + " not found"));
        userAccount.setStatus(newStatus);
        return userAccountRepository.save(userAccount);
    }
}
