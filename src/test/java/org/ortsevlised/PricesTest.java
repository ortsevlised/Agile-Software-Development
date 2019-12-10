package org.ortsevlised;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricesTest {

    @ParameterizedTest
    @CsvSource({"0,0",
            "3443,432",
            "-234,12323",
    })
    public void getUnder25SurchargeTest(int basicInsurance, int basicSurcharge) {
        Prices prices = new Prices(basicInsurance, basicSurcharge);
        assertEquals(prices.getUnder25Surcharge(), basicInsurance + basicSurcharge);
    }

}