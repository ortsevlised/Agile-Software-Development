package org.ortsevlised;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        AccidentSurchargeTest.class,
        InsuranceProgramTest.class,
        InsurancePriceTest.class
})
public class RunnerTest {
}
