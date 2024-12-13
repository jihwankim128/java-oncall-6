package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.workassigndate.HolidayInformation;
import oncall.domain.worker.Worker;
import oncall.domain.worker.Workers;
import oncall.exception.BadArgumentException;
import oncall.exception.ExceptionMessage;

public class OnCall {

    private final Workers weekendsWorkers;
    private final Workers holidayWorkers;

    public OnCall(final Workers weekendsWorkers, final Workers holidayWorkers) {
        validateContainsAllWorkers(weekendsWorkers, holidayWorkers);
        this.weekendsWorkers = weekendsWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    private static void validateContainsAllWorkers(final Workers weekendsWorkers, final Workers holidayWorkers) {
        if (weekendsWorkers.notContainsAll(holidayWorkers)) {
            throw new BadArgumentException(ExceptionMessage.INVALID_INPUT_DATA);
        }
    }

    public List<String> organizeSchedule(final List<HolidayInformation> holidayInformation) {
        final List<Worker> workers = new ArrayList<>();
        for (final HolidayInformation information : holidayInformation) {
            workers.add(getWorker(information.holiday(), workers));
        }

        return workers.stream()
                .map(Worker::getName)
                .toList();
    }

    private Worker getWorker(final boolean holiday, final List<Worker> workers) {
        if (holiday) {
            return getHolidayWorker(workers);
        }
        return getWeekendsWorker(workers);
    }

    private Worker getWeekendsWorker(final List<Worker> workers) {
        final Worker worker = weekendsWorkers.getWorker();
        if (isLastWorker(workers, worker)) {
            return weekendsWorkers.getNextWorker(worker);
        }
        return worker;
    }

    private Worker getHolidayWorker(final List<Worker> workers) {
        final Worker worker = holidayWorkers.getWorker();
        if (isLastWorker(workers, worker)) {
            return holidayWorkers.getNextWorker(worker);
        }
        return worker;
    }

    private boolean isLastWorker(final List<Worker> workers, final Worker worker) {
        if (workers.isEmpty()) {
            return false;
        }
        return workers.get(workers.size() - 1).equals(worker);
    }

}
