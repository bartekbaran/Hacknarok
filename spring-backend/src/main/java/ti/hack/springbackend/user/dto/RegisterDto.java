package ti.hack.springbackend.user.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class RegisterDto {

    private final String email;
    private final String nickname;
    private final String firstname;
    private final String lastname;
    private final String phoneNumber;
    @ToString.Exclude
    private final String password;
}
