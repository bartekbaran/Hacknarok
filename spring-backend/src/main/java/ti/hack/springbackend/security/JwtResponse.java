package ti.hack.springbackend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8502200339706576758L;

    private final String jwtToken;
}