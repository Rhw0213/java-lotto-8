package lotto.view.Console;

import lotto.infra.io.Output;

public class OutputConsole implements Output {
    @Override
    public void output(final String result) {
        System.out.println(result);
    }
}
