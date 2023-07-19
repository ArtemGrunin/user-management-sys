package ua.com.usermanagementsystem.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAccountRequestDto {
    @NotNull
    @Size(min = 3, max = 16, message = "name size must be from 3 to 16 symbols")
    @Pattern(regexp = "[a-zA-Z]*", message = "name must contains only latin letters")
    @Column(unique = true, length = 16)
    private String username;
    @NotNull
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$")
    @Column(length = 16)
    private String password;
    @NotNull
    @Size(min = 1, max = 16, message = "size firstname must be 1-16 symbols")
    @Pattern(regexp = "[a-zA-Z]*", message = "Firstname must contains only latin letters")
    @Column(length = 16)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 16, message = "Size lastname must be 1-16 symbols")
    @Pattern(regexp = "[a-zA-Z]*", message = "Lastname must contains only latin letters")
    @Column(length = 16)
    private String lastName;
    private String role;
    private String status;
}
