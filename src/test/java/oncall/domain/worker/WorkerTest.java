package oncall.domain.worker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.exception.ExceptionMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class WorkerTest {

    @ParameterizedTest
    @NullAndEmptySource
    void 근무자_이름은_비어있거나_null값_일_수_없다(String name) {
        // given
        // when & then
        assertThatThrownBy(() -> Worker.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

    @Test
    void 근무자_이름은_5글자를_초과할_수_없다() {
        // given
        String name = "1".repeat(6);

        // when && then
        assertThatThrownBy(() -> Worker.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ExceptionMessage.INVALID_INPUT_DATA.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void 근무자_이름은_1글자에서_5글자면_정상적으로_생성할_수_있다(int repeatCount) {
        // given
        String name = "1".repeat(repeatCount);

        // when
        Worker worker = Worker.from(name);

        // then
        assertThat(worker.getName()).isEqualTo(name);
    }

}