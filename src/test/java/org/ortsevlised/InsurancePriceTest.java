package org.ortsevlised;

import jline.internal.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsurancePriceTest {
    @BeforeAll
    static void setup() {
        Log.info(">>> Started tests for: "+InsurancePrice.class);
    }

    @AfterAll
    static void tearDown() {
        Log.info(">>> Finished tests for: "+InsurancePrice.class);
    }

    @DisplayName("Testing the getUnder25Surcharge")
    @ParameterizedTest
    @CsvSource({"0,0",
            "3443,432",
            "-234,12323",
    })
    public void getUnder25SurchargeTest(int basicInsurance, int basicSurcharge) {
        InsurancePrice prices = new InsurancePrice(basicInsurance, basicSurcharge);
        assertEquals(prices.getUnder25Surcharge(), basicInsurance + basicSurcharge);
    }
}