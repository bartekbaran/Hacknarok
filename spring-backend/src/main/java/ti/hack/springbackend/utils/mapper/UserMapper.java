package ti.hack.springbackend.utils.mapper;

import ti.hack.springbackend.user.User;
import ti.hack.springbackend.user.dto.UserDto;

import java.util.Objects;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Mapper class");
    }

    public static UserDto userToUserDto(final User user){
        if(Objects.isNull(user)) {
            return null;
        }

        return new UserDto(user.getEmail(), user.getNickname(),
                user.getFirstname(), user.getLastname(),
                user.getRole(), user.getCreated(), user.getLastLogin());
    }

}
