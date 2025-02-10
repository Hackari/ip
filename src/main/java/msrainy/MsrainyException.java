package msrainy;

/**
 * Represents a custom exception specific to the Msrainy application.
 */
public class MsrainyException extends Exception {
    /**
     * Constructs a new MsrainyException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public MsrainyException(String message) {
        super(message);
    }
}
