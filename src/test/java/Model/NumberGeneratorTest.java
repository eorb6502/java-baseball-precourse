package Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberGeneratorTest {
    @Test
    void generateFixedNumberReturnsSameValueWhenValid() {
        NumberGenerator generator = new NumberGenerator();

        assertThat(generator.GenerateFixedNumber("123")).isEqualTo("123");
    }

    @ParameterizedTest
    @ValueSource(strings = {"12", "1234", "012", "11", "1a3", "9 1", "990"})
    void generateFixedNumberRejectsInvalidValues(String value) {
        NumberGenerator generator = new NumberGenerator();

        assertThatThrownBy(() -> generator.GenerateFixedNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void generateFixedNumberRejectsNull() {
        NumberGenerator generator = new NumberGenerator();

        assertThatThrownBy(() -> generator.GenerateFixedNumber(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
