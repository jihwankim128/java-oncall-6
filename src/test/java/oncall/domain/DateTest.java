package oncall.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateTest {

    @ParameterizedTest
    @CsvSource({"일, SUNDAY", "월, MONDAY", "화, TUESDAY", "수, WEDNESDAY", "목, THURSDAY", "금, FRIDAY", "토, SATURDAY"})
    void 날짜는_일요일부터_토요일까지_있다(String date, Date expected) {
        // given
        // when
        Date result = Date.of(date);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 날짜가_아닌_다른_정보라면_예외가_발생한다() {
        // given
        String date = "anyString";

        // when & then
        assertThatThrownBy(() -> Date.of(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"일, 1", "월, 2", "화, 3", "수, 4", "목, 5", "금, 6", "토, 7"})
    void 각_달의_날짜를_알_수_있다(String date, int sequence) {
        // given
        // when
        Date result = Date.of(date);

        // then
        assertThat(result.getSequence()).isEqualTo(sequence);
    }


}