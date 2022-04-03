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

    public static class UserNotFoundException extends UserException {
        public UserNotFoundException(final String email) {
            super(String.format("No user found for email=%s", email));
        }
    }

    public static class PasswordDoNotMatchException extends UserException {
        public PasswordDoNotMatchException() {
            super("Passwords doesn't match");
        }
    }

    public static class UserNotActiveException extends UserException {
        public UserNotActiveException(final String email) {
            super(String.format("User with email=%s, is not confirmed", email));
        }
    }

    public static class InvalidPermissionsException extends UserException {
        public InvalidPermissionsException() {
            super("Invalid permission to do that action!");
        }
    }
}
