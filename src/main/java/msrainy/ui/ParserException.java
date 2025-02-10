package msrainy.ui;

import msrainy.MsrainyException;

/**
 * Represents an exception that occurs during command parsing in the application.
 */
public class ParserException extends MsrainyException {
    /**
     * Constructs a ParserException with the specified error message.
     *
     * @param message The error message describing the parsing issue.
     */
    public ParserException(String message) {
        super(message);
    }
}
