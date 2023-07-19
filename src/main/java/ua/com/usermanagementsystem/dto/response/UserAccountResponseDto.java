package ua.com.usermanagementsystem.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.usermanagementsystem.model.RoleName;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private RoleName role;
    private String status;
    private LocalDateTime createdAt;
}
