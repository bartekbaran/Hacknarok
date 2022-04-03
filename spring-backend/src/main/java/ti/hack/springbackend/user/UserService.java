package ti.hack.springbackend.user;

import lombok.AllArgsConstructor;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ti.hack.springbackend.email.EmailSender;
import ti.hack.springbackend.exception.UserException;
import ti.hack.springbackend.user.dto.LoginDto;
import ti.hack.springbackend.user.dto.RegisterDto;
import ti.hack.springbackend.user.dto.UserDto;
import ti.hack.springbackend.user.token.ConfirmationTokenService;
import ti.hack.springbackend.utils.EmailValidator;
import ti.hack.springbackend.utils.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static ti.hack.springbackend.config.ConfigEnum.HOSTNAME;

@Service
@AllArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static final String EMAIL_LINK = HOSTNAME.getValue() + "/api/v1/user/confirm?token=%s";

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    @Transactional(rollbackFor = PSQLException.class)
    public void createUser(final RegisterDto registerDto) {
        LOGGER.info("createUser() starting user creation for email={}", registerDto.getEmail());

        if (!this.emailValidator.test(registerDto.getEmail())) {
            LOGGER.info("createUser() invalid email form - {}", registerDto.getEmail());
            throw new UserException.InvalidEmailFormException(registerDto.getEmail());
        }

        if (this.userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            LOGGER.info("createUser() user with this email - {}, already exists", registerDto.getEmail());
            throw new UserException.EmailAlreadyTakenException(registerDto.getEmail());
        }

        if (this.userRepository.findByNickname(registerDto.getNickname()).isPresent()) {
            LOGGER.info("createUser() user with nickname - {}, already exists", registerDto.getNickname());
            throw new UserException.NicknameAlreadyTakenException(registerDto.getNickname());
        }

        final String encryptedPassword = passwordEncoder.encode(registerDto.getPassword());

        final User createdUser = new User(registerDto.getEmail(), registerDto.getNickname(), registerDto.getFirstname(),
                registerDto.getLastname(), registerDto.getPhoneNumber(), encryptedPassword,
                UserRole.USER.toString(), LocalDateTime.now(), null, false);

        this.userRepository.save(createdUser);
        LOGGER.debug("createUser() user created successfully!");

        final String token = this.confirmationTokenService.createConfirmationToken(createdUser.getUserId());
        LOGGER.info("createUser() confirmation token created for user with id={}", createdUser.getUserId());

        this.emailSender.send(createdUser.getEmail(), this.buildEmail(createdUser.getNickname(), String.format(EMAIL_LINK, token)));
    }

    public UserDto login(final LoginDto request) {
        LOGGER.info("login() login process for email={}", request.getEmail());

        final Optional<User> optionalUser = this.userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            LOGGER.info("login() no user found for email={}", request.getEmail());
            throw new UserException.UserNotFoundException(request.getEmail());
        }

        if (!optionalUser.get().isConfirmed()) {
            LOGGER.info("login() user with email={}, is not confirmed", request.getEmail());
            throw new UserException.UserNotActiveException(request.getEmail());
        }

        if (!this.passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            LOGGER.info("login() password doesn't match");
            throw new UserException.PasswordDoNotMatchException();
        }

        LOGGER.info("login() updating last login");
        this.userRepository.updateLastLogin(LocalDateTime.now(), optionalUser.get().getUserId());

        return UserMapper.userToUserDto(optionalUser.get());
    }

    public void confirmEmail(final String token) {
        final Long userId = this.confirmationTokenService.setConfirmed(token);

        LOGGER.info("confirmEmail() enabling user with userId={}", userId);
        this.userRepository.enableAppUser(userId);
    }

    public void changePassword(final Authentication authentication, final String newPassword) {

        final Optional<User> optionalUser = this.userRepository.findByEmail(authentication.getName());

        if (optionalUser.isEmpty()) {
            LOGGER.info("login() no user found for email={}", authentication.getName());
            throw new UserException.UserNotFoundException(authentication.getName());
        }

        this.userRepository.updatePassword(authentication.getName(), newPassword);
        LOGGER.info("changePassword() password changed successfully for user with email={}", authentication.getName());
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
