package ti.hack.springbackend.user.token;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ti.hack.springbackend.exception.TokenException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmationTokenService.class);

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(final ConfirmationToken token) {
        LOGGER.info("saveConfirmationToken() saving token={}", token);

        if (this.confirmationTokenRepository.findByToken(token.getToken()).isPresent()) {
            LOGGER.info("token={} is already taken", token);
            throw new TokenException.TokenAlreadyTakenException(token.getToken());
        }

        this.confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(final String token) {
        LOGGER.debug("getToken() getting token={}", token);
        return confirmationTokenRepository.findByToken(token).orElse(null);

    }

    public Long setConfirmed(final String token) {
        LOGGER.debug("setConfirmed() updating token={}", token);
        this.confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
        LOGGER.info("token={} has been successfully updated", token);

        final Optional<ConfirmationToken> optionalConfirmationToken = this.confirmationTokenRepository.findByToken(token);

        if (optionalConfirmationToken.isPresent()) {
            return optionalConfirmationToken.get().getUid();
        }
        LOGGER.warn("setConfirmed() token={}, doesn't exist", token);
        return null;

    }

    public String createConfirmationToken(final Long userId) {
        final String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), userId);
        saveConfirmationToken(confirmationToken);

        return token;
    }
}
