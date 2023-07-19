package ua.com.usermanagementsystem.service.mapper.impl;

import org.springframework.stereotype.Component;
import ua.com.usermanagementsystem.dto.response.RoleResponseDto;
import ua.com.usermanagementsystem.model.Role;
import ua.com.usermanagementsystem.service.mapper.ResponseDtoMapper;

@Component
public class RoleMapper implements ResponseDtoMapper<RoleResponseDto, Role> {

    @Override
    public RoleResponseDto mapToDto(Role role) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getName().name());
        return dto;
    }
}
