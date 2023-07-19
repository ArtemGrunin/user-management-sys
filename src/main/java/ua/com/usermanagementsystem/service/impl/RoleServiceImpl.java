package ua.com.usermanagementsystem.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.usermanagementsystem.exception.UserAccountNotFoundException;
import ua.com.usermanagementsystem.model.Role;
import ua.com.usermanagementsystem.model.RoleName;
import ua.com.usermanagementsystem.repository.RoleRepository;
import ua.com.usermanagementsystem.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new UserAccountNotFoundException("Role with id:" + id + " not found"));
    }

    @Override
    public Role getByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
