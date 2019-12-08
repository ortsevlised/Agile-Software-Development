import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class InsuranceProgramTest {

    @DisplayName("Testing the total amount to pay - Happy path")
    @ParameterizedTest
    @CsvSource({
            "500,0,500",
            "100,1,150",
            "200,2,325",
            "300,3,525",
            "400,4,775",
            "500,5,1075",
            "500,6,-1",
            "500,7,-1",
            "4400,27,-1",
            "500,-1,-1",

    })
    void getAmountToPayTest(int basicAmount, int numberOfAccidents, int expectedAmount) {
        int totalAmount = InsuranceProgram.getAmountToPay(basicAmount, numberOfAccidents);
        assertThat("The total amount should be", totalAmount, is(expectedAmount));
    }

    @DisplayName("Testing input of a valid number")
    @ParameterizedTest
    @CsvSource({
            "5,5",
            "0,0",
            "70,70"
    })
    public void shouldTakeUserInputTest(String input, String expectedValue) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThat("5", InsuranceProgram.getUserInput(), is(Integer.valueOf(expectedValue)));
    }

    @Test
    @DisplayName("Testing error message is displayed when using an invalid input")
    public void shouldTakeUserInputTestNegativeScenario() throws UnsupportedEncodingException {
        ByteArrayInputStream in = new ByteArrayInputStream("5s\n5".getBytes());
        System.setIn(in);
        ByteArrayOutputStream mockOut = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(mockOut);
        System.setOut(newOut);
        assertThat("5", InsuranceProgram.getUserInput(), is(Integer.valueOf(5)));
        assertTrue(new String(mockOut.toByteArray(), "UTF-8").contains(Constants.PLEASE_ENTER_A_VALID_NUMBER));

    }


}
