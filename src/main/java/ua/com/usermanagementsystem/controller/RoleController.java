package ua.com.usermanagementsystem.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.usermanagementsystem.dto.response.RoleResponseDto;
import ua.com.usermanagementsystem.model.Role;
import ua.com.usermanagementsystem.service.RoleService;
import ua.com.usermanagementsystem.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ResponseDtoMapper<RoleResponseDto, Role> roleResponseDtoMapper;

    @GetMapping
    public List<RoleResponseDto> findAll() {
        return roleService.findAll().stream()
                .map(roleResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
