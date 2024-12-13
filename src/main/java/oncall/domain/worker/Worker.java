package oncall.domain.worker;

import java.util.Objects;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public class Worker {

    private static final int MAX_WORKER_NAME_LENGTH = 5;
    private final String name;

    private Worker(final String name) {
        validateWorkerName(name);
        this.name = name;
    }

    public static Worker from(final String name) {
        return new Worker(name);
    }

    private static void validateWorkerName(final String name) {
        validateWorkerNameNullOrEmpty(name);
        validateWorkerNameLength(name);
    }

    private static void validateWorkerNameNullOrEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    private static void validateWorkerNameLength(final String name) {
        if (name.length() > MAX_WORKER_NAME_LENGTH) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Worker worker = (Worker) object;
        return Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
