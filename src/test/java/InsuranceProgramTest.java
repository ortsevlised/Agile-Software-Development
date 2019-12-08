import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class InsuranceProgramTest {



    @DisplayName("Testing the total amount to pay - Happy path")
    @ParameterizedTest
    @CsvSource({"500,0,500",
            "100,1,150",
            "200,2,325",
            "300,3,525",
            "400,4,775",
            "500,5,1075"
    })
   void getAmountToPayTest(int basicAmount, int numberOfAccidents, int expectedAmount) {
        int totalAmount = InsuranceProgram.getAmountToPay(basicAmount, numberOfAccidents);
        assertThat("The total amount should be", totalAmount, is(expectedAmount));
    }
}
