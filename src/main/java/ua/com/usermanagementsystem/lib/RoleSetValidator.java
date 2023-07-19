package ua.com.usermanagementsystem.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import ua.com.usermanagementsystem.exception.UserAccountNotFoundException;
import ua.com.usermanagementsystem.service.RoleService;

@RequiredArgsConstructor
public class RoleSetValidator implements ConstraintValidator<ValidRolesSet, Set<Long>> {
    private final RoleService roleService;

    @Override
    public boolean isValid(Set<Long> roleIds,
                           ConstraintValidatorContext context) {
        if (roleIds == null || roleIds.isEmpty()) {
            return false;
        }
        for (Long id : roleIds) {
            try {
                roleService.findById(id);
            } catch (UserAccountNotFoundException e) {
                return false;
            }
        }
        return true;
    }
}
