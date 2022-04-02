package ti.hack.springbackend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = -4786320370893263681L;

    private String nickname;
    private String password;

    public JwtRequest() {
    }

}