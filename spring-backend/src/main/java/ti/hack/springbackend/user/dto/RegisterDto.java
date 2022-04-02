package ti.hack.springbackend.user.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class RegisterDto {

    private final String email;
    private final String nickname;
    @ToString.Exclude
    private final String password;
}
