package oncall.domain.worker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.OnCall;
import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Test;

class WorkersTest {

    @Test
    void 중복된_근무자_명단이_존재할_수_없다() {
        // given
        List<String> workerNames = List.of("근무자1", "근무자2", "근무자1");

        // when & then
        assertThatThrownBy(() -> Workers.from(workerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

    @Test
    void 근무자가_5명_아래일_수_없다() {
        // given
        List<String> workerNames = List.of("근무자1", "근무자2", "근무자3", "근무자4");

        // when
        assertThatThrownBy(() -> Workers.from(workerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

    @Test
    void 근무자가_35명을_초과할_수_없다() {
        // given
        List<String> workerNames = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            workerNames.add("근무자" + i);
        }

        // when & Then
        assertThatThrownBy(() -> Workers.from(workerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }


    @Test
    void 근무자는_최소_5명이다() {
        // given
        List<String> workerNames = new ArrayList<>();
        int workersSize = 5;

        for (int i = 0; i < workersSize; i++) {
            workerNames.add("근무자" + i);
        }

        // when
        Workers workers = Workers.from(workerNames);

        // then
        assertThat(workers.getWorkers()).hasSize(workersSize);
    }

    @Test
    void 근무자는_최대_35명이다() {
        // given
        List<String> workerNames = new ArrayList<>();
        int workersSize = 35;

        for (int i = 0; i < workersSize; i++) {
            workerNames.add("근무자" + i);
        }

        // when
        Workers workers = Workers.from(workerNames);

        // then
        assertThat(workers.getWorkers()).hasSize(workersSize);
    }

    @Test
    void 휴일근무자가_평일근무자에_포함되어있는지_알_수_있다() {
        // given
        Workers weekendsWorkers = Workers.from(List.of("근무자1", "근무자2", "근무자3", "근무자4", "근무자5"));
        Workers holidayWorkers = Workers.from(List.of("근무자6", "근무자2", "근무자3", "근무자4", "근무자5"));

        // when
        boolean result = weekendsWorkers.notContainsAll(holidayWorkers);

        // then
        assertThat(result).isTrue();
    }
}