package org.ortsevlised;

import java.util.HashMap;

import static org.ortsevlised.Constants.*;
import static org.ortsevlised.ExtraPerAccident.*;

public class AccidentSurcharge {
    static HashMap<Integer, ToPay> getAccidentSurcharge(int currentPrice) {
        HashMap<Integer, ToPay> ACCIDENTS =
                new HashMap<>();
        {
            ACCIDENTS.put(0, new ToPay(String.format("%s\n%s", NO_SURCHARGE, TOTAL_AMOUNT_TO_PAY + currentPrice), currentPrice));
            ACCIDENTS.put(1, getValue(currentPrice, ONE_ACCIDENT.getExtraToPay()));
            ACCIDENTS.put(2, getValue(currentPrice, TWO_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(3, getValue(currentPrice, THREE_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(4, getValue(currentPrice, FOUR_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(5, getValue(currentPrice, FIVE_ACCIDENTS.getExtraToPay()));
        }
        return ACCIDENTS;
    }

    private static ToPay getValue(int currentPrice, int extraPerAccident) {
        int amount = currentPrice + extraPerAccident;
        return new ToPay(ADDITIONAL_SURCHARGE_FOR_ACCIDENT + extraPerAccident +
                TOTAL_AMOUNT_TO_PAY + amount, amount);
    }
}
