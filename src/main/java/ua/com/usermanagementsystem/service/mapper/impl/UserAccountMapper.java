package ua.com.usermanagementsystem.service.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.com.usermanagementsystem.dto.request.UserAccountRequestDto;
import ua.com.usermanagementsystem.dto.response.UserAccountResponseDto;
import ua.com.usermanagementsystem.model.RoleName;
import ua.com.usermanagementsystem.model.Status;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.service.RoleService;
import ua.com.usermanagementsystem.service.mapper.RequestDtoMapper;
import ua.com.usermanagementsystem.service.mapper.ResponseDtoMapper;

@Component
@RequiredArgsConstructor
public class UserAccountMapper implements RequestDtoMapper<UserAccountRequestDto, UserAccount>,
        ResponseDtoMapper<UserAccountResponseDto, UserAccount> {

    private final RoleService roleService;

    @Override
    public UserAccount mapToModel(UserAccountRequestDto dto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername((dto.getUsername()));
        userAccount.setPassword(dto.getPassword());
        userAccount.setFirstName(dto.getFirstName());
        userAccount.setLastName(dto.getLastName());
        userAccount.setStatus(Status.valueOf(dto.getStatus()));
        userAccount.setRole(roleService.getByName(RoleName.valueOf(dto.getRole().toUpperCase())));
        return userAccount;
    }

    @Override
    public UserAccountResponseDto mapToDto(UserAccount user) {
        UserAccountResponseDto dto = new UserAccountResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole().getName());
        dto.setStatus(user.getStatus().name());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
