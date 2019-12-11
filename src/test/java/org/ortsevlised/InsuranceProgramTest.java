package org.ortsevlised;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.ortsevlised.Constants.HOW_MANY_ACCIDENTS_DID_YOU_HAVE;
import static org.ortsevlised.Constants.PLEASE_ENTER_A_VALID_NUMBER;
import static org.ortsevlised.ExtraPerAccident.*;

public class InsuranceProgramTest {
    static Prices price;
    private static final ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    @BeforeAll
    static void setup() {
        price = new Prices(500);
        PrintStream newOut = new PrintStream(mockOut);
        System.setOut(newOut);
    }

    @AfterAll
    static void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @DisplayName("Testing the total amount to pay - Happy path")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void getInsuranceToPayTest(int numberOfAccidents) {
        ToPay insuranceToPay = InsuranceProgram.getInsuranceToPay(numberOfAccidents, price.getCurrentPrice());
        assertThat("The total amount should be", insuranceToPay.getAmount(), is(price.getCurrentPrice() + values()[numberOfAccidents].getExtraToPay()));
    }

    @DisplayName("Testing the total amount to pay - Negative path")
    @ParameterizedTest
    @ValueSource(ints = {-1, 6, 232})
    void getInsuranceToPayTestNegativeScenarios(int numberOfAccidents) {
        ToPay insuranceToPay = InsuranceProgram.getInsuranceToPay(numberOfAccidents, price.getCurrentPrice());
        assertThat("I get -1 as a flag value", insuranceToPay.getAmount(), is(MORE_ACCIDENTS.getExtraToPay()));
    }

    @DisplayName("Testing input of a valid number")
    @ParameterizedTest
    @CsvSource({
            "5,5",
            "0,0",
            "70,70"
    })
    public void shouldTakeUserInputTest(String input, String expectedValue) throws TooManyRetriesException {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat("5", InsuranceProgram.getUserInput(), is(Integer.valueOf(expectedValue)));
    }

    @Test
    @DisplayName("Testing error message is displayed when using an invalid input")
    public void shouldTakeUserInputTestNegativeScenario() throws TooManyRetriesException {
        ByteArrayInputStream in = new ByteArrayInputStream("5s\n5".getBytes());
        System.setIn(in);
        assertThat("5", InsuranceProgram.getUserInput(), is(5));
        assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains(PLEASE_ENTER_A_VALID_NUMBER));
    }

    @Test
    @DisplayName("Testing TooManyRetriesException is thrown when retrying more than 50 times")
    public void getUserInputThrowsTooManyRetriesException() {
        String str = "test\n";
        String repeated = StringUtils.repeat(str, 50);
        ByteArrayInputStream in = new ByteArrayInputStream(repeated.getBytes());
        System.setIn(in);
        assertThrows(TooManyRetriesException.class, () -> InsuranceProgram.getUserInput());
    }


    @DisplayName("Testing the print messages method with Strings")
    @Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
    @ParameterizedTest
    @ValueSource(strings = {"Hi", "break\nline", "232"})
    public void printMessagesTest(String message) {
        System.out.println(message);
        System.out.print(HOW_MANY_ACCIDENTS_DID_YOU_HAVE);
        assertTrue(new String(mockOut.toByteArray(), StandardCharsets.UTF_8).contains(message + "\n" + HOW_MANY_ACCIDENTS_DID_YOU_HAVE));
    }


}
