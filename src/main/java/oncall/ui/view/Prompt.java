package oncall.ui.view;

import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public interface Prompt {

    String readInputData();

    default void validateNotNullOrNotEmpty(final String data) {
        if (data == null || data.isEmpty()) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

}