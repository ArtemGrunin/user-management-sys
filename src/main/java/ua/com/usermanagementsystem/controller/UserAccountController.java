package ua.com.usermanagementsystem.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.usermanagementsystem.dto.request.UserAccountRequestDto;
import ua.com.usermanagementsystem.dto.response.UserAccountResponseDto;
import ua.com.usermanagementsystem.model.Status;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.service.UserAccountService;
import ua.com.usermanagementsystem.service.mapper.RequestDtoMapper;
import ua.com.usermanagementsystem.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final RequestDtoMapper<UserAccountRequestDto, UserAccount> userAccountRequestMapper;
    private final ResponseDtoMapper<UserAccountResponseDto, UserAccount> userAccountResponseMapper;

    @PostMapping("/new")
    public UserAccountResponseDto create(@Valid @RequestBody UserAccountRequestDto dto) {
        UserAccount user = userAccountRequestMapper.mapToModel(dto);
        user.setStatus(Status.ACTIVE);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userAccountResponseMapper.mapToDto(userAccountService.add(user));
    }

    @GetMapping("/{id}")
    public UserAccountResponseDto get(@PathVariable Long id) {
        return userAccountResponseMapper.mapToDto(userAccountService.get(id));
    }

    @PutMapping("/{id}/edit")
    public UserAccountResponseDto update(@PathVariable Long id,
                                         @Valid @RequestBody UserAccountRequestDto dto) {
        UserAccount userAccount = userAccountRequestMapper.mapToModel(dto);
        userAccount.setId(id);
        return userAccountResponseMapper.mapToDto(userAccountService.update(userAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userAccountService.delete(id);
        return ResponseEntity.ok("UserAccount with id " + id + " was deleted");
    }

    @GetMapping
    public List<UserAccountResponseDto> getAll(@RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "5") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userAccountService.findAll(pageRequest).stream()
                .map(userAccountResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public UserAccountResponseDto changeStatus(@PathVariable Long id,
                                               @RequestParam Status newStatus) {
        return userAccountResponseMapper
                .mapToDto(userAccountService.changeStatus(id, newStatus));
    }
}
