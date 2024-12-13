package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.exception.ExceptionMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MonthTest {

    @ParameterizedTest
    @CsvSource({
            "1, JANUARY", "2, FEBRUARY", "3, MARCH", "4, APRIL", "5, MAY", "6, JUNE", "7, JULY",
            "8, AUGUST", "9, SEPTEMBER", "10, OCTOBER", "11, NOVEMBER", "12, DECEMBER"
    })
    void Month는_1월부터_12월까지_존재한다(String month, Month expected) {
        // given
        // when
        Month result = Month.of(month);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0","13","string"})
    void Month는_1부터_12_사이의_값이_아니라면_예외가_발생한다(String month) {
        // given
        // when & then
        assertThatThrownBy(() -> Month.of(month))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 31", "2, 28", "3, 31", "4, 30", "5, 31", "6, 30", "7, 31",
            "8, 31", "9, 30", "10, 31", "11, 30", "12, 31"
    })
    void 각_달의_날짜를_알_수_있다(String month, int day) {
        // given
        // when
        Month result = Month.of(month);

        // then
        assertThat(result.getDay()).isEqualTo(day);
    }

}