package org.example.junit5;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

public class StepTest {

    private static final String GLOBAL_PARAMETER = "глобальное значение";

    @Test
    public void annotatedStepTest() {
        annotatedStep("локальное значение");
    }

    @Test
    public void lambdaStepTest() {
        final String localParameter = "значение параметра";
        Allure.step(String.format("Родительский lambda-шаг с параметром [%s]", localParameter), (step) -> {
            step.parameter("параметр", localParameter);
            Allure.step(String.format("Вложенный lambda-шаг с глобальным параметром [%s]", GLOBAL_PARAMETER));
        });
    }

    @Step("Родительский аннотированный шаг с параметром [{parameter}]")
    public void annotatedStep(final String parameter) {
        nestedAnnotatedStep();
    }

    @Step("Вложенный аннотированный шаг с глобальным параметром [{this.GLOBAL_PARAMETER}]")
    public void nestedAnnotatedStep() {

    }

}
