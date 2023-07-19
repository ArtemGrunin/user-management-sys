package ua.com.usermanagementsystem.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public UserAccount add(UserAccount userAccount) {
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
        userAccountRepository.deleteById(id);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        if (userAccountRepository.existsById(userAccount.getId())) {
            return userAccountRepository.save(userAccount);
        } else {
            throw new RuntimeException(
                    "User with id " + userAccount.getId() + " does not exist");
        }
    }

    @Override
    public UserAccount findByUsername(String name) {
        UserAccount user = userAccountRepository.findByUsername(name);
        if (user == null) {
            throw new UserAccountNotFoundException("User with id: " + name + " not found");
        }
        return user;
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
