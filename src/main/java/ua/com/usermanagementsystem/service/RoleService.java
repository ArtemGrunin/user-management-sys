package ua.com.usermanagementsystem.service;

import java.util.List;
import ua.com.usermanagementsystem.model.Role;
import ua.com.usermanagementsystem.model.RoleName;

public interface RoleService {
    Role add(Role role);

    Role findById(Long id);

    Role getByName(RoleName roleName);

    List<Role> findAll();
}
