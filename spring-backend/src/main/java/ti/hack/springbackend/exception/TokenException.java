package ti.hack.springbackend.exception;

public class TokenException extends RuntimeException {

    public TokenException(final String message) {
        super(message);
    }

    public static class TokenAlreadyTakenException extends TokenException {
        public TokenAlreadyTakenException(final String token) {
            super(String.format("User with token=%s already exists", token));
        }
    }



}
