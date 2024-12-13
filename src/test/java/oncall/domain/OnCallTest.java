package oncall.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import oncall.domain.worker.Workers;
import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Test;

class OnCallTest {

    @Test
    void 휴일근무자가_평일근무자에_포함되어있지_않을_경우_편성할_수_없다() {
        // given
        Workers weekendsWorkers = Workers.from(List.of("근무자1", "근무자2", "근무자3", "근무자4", "근무자5"));
        Workers holidayWorkers = Workers.from(List.of("근무자6", "근무자2", "근무자3", "근무자4", "근무자5"));

        // when & then
        assertThatThrownBy(() -> new OnCall(weekendsWorkers, holidayWorkers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

}