package oncall.domain;

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

}
