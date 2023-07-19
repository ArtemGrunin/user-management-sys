package ua.com.usermanagementsystem.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.usermanagementsystem.dto.request.StatusRequestDto;
import ua.com.usermanagementsystem.dto.request.UserAccountRequestDto;
import ua.com.usermanagementsystem.dto.response.UserAccountResponseDto;
import ua.com.usermanagementsystem.model.UserAccount;
import ua.com.usermanagementsystem.service.UserAccountService;
import ua.com.usermanagementsystem.service.mapper.RequestDtoMapper;
import ua.com.usermanagementsystem.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final RequestDtoMapper<UserAccountRequestDto, UserAccount> userAccountRequestMapper;
    private final ResponseDtoMapper<UserAccountResponseDto, UserAccount> userAccountResponseMapper;

    @PostMapping
    public UserAccountResponseDto create(@Valid @RequestBody UserAccountRequestDto dto) {
        UserAccount user = userAccountRequestMapper.mapToModel(dto);
        return userAccountResponseMapper.mapToDto(userAccountService.add(user));
    }

    @GetMapping("/{id}")
    public UserAccountResponseDto get(@PathVariable Long id) {
        return userAccountResponseMapper.mapToDto(userAccountService.get(id));
    }

    @PutMapping("/{id}")
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
    public List<UserAccountResponseDto> getAll(
            @RequestParam(defaultValue = "1")
            @Min(value = 1, message = "Page number should not be less than 1") Integer page,
            @RequestParam(defaultValue = "5")
            @Min(value = 1, message = "Size should not be less than 1") Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userAccountService.findAll(pageRequest).stream()
                .map(userAccountResponseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public UserAccountResponseDto changeStatus(@PathVariable Long id,
                                               @RequestBody StatusRequestDto statusRequestDto) {
        return userAccountResponseMapper
                .mapToDto(userAccountService.changeStatus(id, statusRequestDto.getStatus()));
    }
}
