package ua.com.usermanagementsystem.dto.request;

        import jakarta.persistence.Column;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.Pattern;
        import jakarta.validation.constraints.Size;
        import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "Username can't be null or blank!")
    @Size(min = 3, max = 16, message = "name size must be from 3 to 16 symbols")
    @Pattern(regexp = "[a-zA-Z]+", message = "name must contains only latin letters")
    private String username;
    @NotBlank(message = "Password can't be null or blank!")
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$")
    @Column(length = 16)
    private String password;
}
