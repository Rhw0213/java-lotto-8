package lotto.validation.input;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidatorTest {
    InputValidator numberAndComaValidator = new NumberAndCommaValidator();
    InputValidator emptyValidator = new EmptyInputValidator();
    InputValidator InputNumbersValidator = new InputNumbersValidator();

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {
            "", " ", "   ",
            "\t", "\n", "\r", "\f", "\u000B",
            "\u00A0", "\u2000", "\u2001", "\u2002", "\u2003",
            "\u2004", "\u2005", "\u2006", "\u2007", "\u2008",
            "\u2009", "\u200A", "\u2028", "\u2029",
            "\u202F", "\u205F", "\u3000"
    })
    void 빈_문자열은_예외발생한다(String input) {
        assertThatThrownBy(() -> emptyValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationRule.BLANK.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "9", "0",
            "1,2,3,4,5",
            "10,20,30",
            "123,456,789",
            "10000,20000,30000",
            "999999,888888",
            "12345",
            "0,1,2,3",
            "1,1,1,1",
            "1,2,3,4,5,6,7,8,9,10,11,12"
    })
    void 번호_입력은_숫자와_쉼표만_가능하다(String input) {
        assertThatCode(() -> numberAndComaValidator.validate(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1.2,3", "a,b,c", "1-2-3", "１,２,３", "1,2,三", "1,2,😊",
            ".1,2,3", ",1,2,3", "1,2,3.", "1,2,3,"
    })
    void 입력문자열은_숫자와_쉼표_이외의_문자는_예외발생_한다(String input) {
        assertThatThrownBy(() -> numberAndComaValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationRule.ONLY_NUMBER_AND_COMMA.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ".1,2,3", ",1,2,3", "1,2,3.", "1,2,3,"
    })
    void 입력문자열_앞뒤로_숫자외에는_예외발생_한다(String input) {
        assertThatThrownBy(() -> numberAndComaValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationRule.ONLY_NUMBER_AND_COMMA.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "010", "00", "0123", "00001", "0",
            "01,2", "1 2", "1.", "'10", "10'", "10a", "10 "
    })
    void 보너스_번호는_숫자가_아니면_예외를_발생한다(String input) {
        assertThatThrownBy(() -> InputNumbersValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ValidationRule.ONLY_NUMBER.getErrorMessage());
    }
}
