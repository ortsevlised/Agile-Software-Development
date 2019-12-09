import org.junit.jupiter.api.BeforeAll;
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
    static Prices price;

    @BeforeAll
    public static void setup() {
        price = new Prices(500);
    }

    @DisplayName("Testing the total amount to pay - Happy path")
    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,50",
            "2,125",
            "3,225",
            "4,375",
            "5,575",
    })
    void getAmountToPayTest(int numberOfAccidents, int surcharge) {
        ToPay insuranceToPay = InsuranceProgram.getMessageToDisplay(numberOfAccidents,price.getCurrentPrice());
        assertThat("The total amount should be", insuranceToPay.getAmount(), is(price.getCurrentPrice() + surcharge));
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
