package oncall.domain.worker;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public class Workers {

    private static final int MIN_WORKERS_SIZE = 5;
    private static final int MAX_WORKERS_SIZE = 35;

    private final List<Worker> workers;
    private final Stack<Worker> workersStack = new Stack<>();
    private int idx = 0;

    private Workers(final List<Worker> workers) {
        validateWorkersSize(workers);
        this.workers = workers;
    }

    public static Workers from(final List<String> workerNames) {
        validateAlreadyIncludeWorkers(workerNames);

        return new Workers(workerNames.stream()
                .map(Worker::from)
                .toList());
    }

    private static void validateAlreadyIncludeWorkers(final List<String> workers) {
        if (new HashSet<>(workers).size() != workers.size()) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    private void validateWorkersSize(final List<Worker> workers) {
        if (workers.size() < MIN_WORKERS_SIZE || workers.size() > MAX_WORKERS_SIZE) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    public List<Worker> getWorkers() {
        return Collections.unmodifiableList(workers);
    }

    public boolean notContainsAll(final Workers holidayWorkers) {
        return !new HashSet<>(workers).containsAll(holidayWorkers.workers);
    }

    public Worker getWorker() {
        if (workersStack.empty()) {
            return workers.get((idx++ % workers.size()));
        }
        return workersStack.pop();
    }

    public Worker getNextWorker(final Worker worker) {
        workersStack.push(worker);
        return workers.get((idx++ % workers.size()));
    }

}
