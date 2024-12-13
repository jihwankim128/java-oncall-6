package oncall.domain.worker;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public class Workers {

    private static final int MIN_WORKERS_SIZE = 5;
    private static final int MAX_WORKERS_SIZE = 35;

    private final Set<Worker> workers;

    private Workers(final Set<Worker> workers) {
        validateWorkersSize(workers);
        this.workers = workers;
    }

    public static Workers from(final List<String> workerNames) {
        final Set<Worker> workers = new LinkedHashSet<>();

        for (final String workerName : workerNames) {
            final Worker worker = Worker.from(workerName);
            validateAlreadyIncludeWorkers(workers, worker);
            workers.add(worker);
        }

        return new Workers(workers);
    }

    private static void validateAlreadyIncludeWorkers(final Set<Worker> workers, final Worker worker) {
        if (workers.contains(worker)) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    private void validateWorkersSize(final Set<Worker> workers) {
        if (workers.size() < MIN_WORKERS_SIZE || workers.size() > MAX_WORKERS_SIZE) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    public Set<Worker> getWorkers() {
        return Collections.unmodifiableSet(workers);
    }

}
