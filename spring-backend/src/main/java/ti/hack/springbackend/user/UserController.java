package ti.hack.springbackend.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ti.hack.springbackend.user.dto.RegisterDto;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/user")
@AllArgsConstructor
public class UserController {

     final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final RegisterDto registerDto) {
        this.userService.createUser(registerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @GetMapping
    public ResponseEntity<?> confirmEmail(@RequestParam("token") final String token) {
        this.userService.confirmEmail(token);

        return ResponseEntity.status(HttpStatus.OK).body("Token verified succesfully");
    }

}
