package oncall.exception;

public class BadArgumentException extends IllegalArgumentException {

    public BadArgumentException(final ExceptionMessage message) {
        super(String.format(ExceptionMessage.EXCEPTION_MESSAGE_FORMAT.getMessage(), message.getMessage()));
    }

}