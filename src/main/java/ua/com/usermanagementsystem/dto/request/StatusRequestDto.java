package ua.com.usermanagementsystem.dto.request;

import lombok.Data;
import ua.com.usermanagementsystem.model.Status;

@Data
public class StatusRequestDto {
    private Status status;
}
