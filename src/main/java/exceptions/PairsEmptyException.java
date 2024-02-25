package exceptions;

public class PairsEmptyException extends RuntimeException {

    public PairsEmptyException() {
    }

    public PairsEmptyException(String message) {
        super(message);
    }

    public PairsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PairsEmptyException(Throwable cause) {
        super(cause);
    }

}
