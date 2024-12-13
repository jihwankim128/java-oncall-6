package oncall.domain.workassigndate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HolidayTest {

    @Test
    void 공휴일을_알_수_있다() {
        // given
        // when
        boolean result = Holiday.isHoliday(Month.MAY, 5);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 공휴일이_아닌_것을_알_수_있다() {
        // given
        // when
        boolean result = Holiday.isHoliday(Month.MAY, 6);

        // then
        assertThat(result).isFalse();
    }

}