package ti.hack.springbackend.exception;

public class TokenException extends RuntimeException {

    public TokenException(final String message) {
        super(message);
    }

    public static class TokenAlreadyExistsException extends TokenException {
        public TokenAlreadyExistsException(final String token) {
            super(String.format("User with token=%s already exists", token));
        }
    }

    public static class TokenNotFoundException extends TokenException {
        public TokenNotFoundException(final String token) {
            super(String.format("Token=%s not found", token));
        }
    }

}
