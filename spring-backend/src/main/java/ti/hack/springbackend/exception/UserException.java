package ti.hack.springbackend.exception;

public class UserException extends RuntimeException {

    public UserException(final String message) {
        super(message);
    }

    public static class EmailAlreadyTakenException extends UserException {
        public EmailAlreadyTakenException(final String email) {
            super(String.format("User with email=%s already exists", email));
        }
    }

    public static class InvalidEmailFormException extends UserException {
        public InvalidEmailFormException(final String incorrectEmail) {
            super(String.format("%s is not an email", incorrectEmail));
        }
    }

    public static class NicknameAlreadyTakenException extends UserException {
        public NicknameAlreadyTakenException(final String nickname) {
            super(String.format("User with nickname=%s is already taken", nickname));
        }
    }
}
