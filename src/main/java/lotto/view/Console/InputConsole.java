package lotto.view.Console;

import camp.nextstep.edu.missionutils.Console;
import lotto.infra.io.Input;
import lotto.validation.input.InputValidator;

public class InputConsole implements Input {
    @Override
    public String input() {
        return Console.readLine();
    }

    @Override
    public void validateFormat(final InputValidator... validators) {

    }
}
