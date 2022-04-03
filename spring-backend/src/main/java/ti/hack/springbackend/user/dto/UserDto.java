package ti.hack.springbackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {

    private final String email;
    private final String nickname;
    private final String firstname;
    private final String lastname;
    private final String role;
    private final LocalDateTime created;
    private LocalDateTime lastLogin;
}
