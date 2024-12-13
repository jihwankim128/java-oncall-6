package oncall.exception;


public enum ExceptionMessage {

    EXCEPTION_MESSAGE_FORMAT("[ERROR] %s"),

    INVALID_INPUT_DATA("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
