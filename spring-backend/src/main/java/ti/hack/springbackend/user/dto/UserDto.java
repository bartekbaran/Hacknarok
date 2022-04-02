package ti.hack.springbackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDto {

    private final String email;
    private final String nickname;
    private final String role;
    private final Date created;
    private Date lastLogin;
}
