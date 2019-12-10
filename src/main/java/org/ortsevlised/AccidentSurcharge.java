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
            ACCIDENTS.put(1, getValueToPay(currentPrice, ONE_ACCIDENT.getExtraToPay()));
            ACCIDENTS.put(2, getValueToPay(currentPrice, TWO_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(3, getValueToPay(currentPrice, THREE_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(4, getValueToPay(currentPrice, FOUR_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(5, getValueToPay(currentPrice, FIVE_ACCIDENTS.getExtraToPay()));
        }
        return ACCIDENTS;
    }

    private static ToPay getValueToPay(int currentPrice, int extraPerAccident) {
        int amount = currentPrice + extraPerAccident;
        String message = ADDITIONAL_SURCHARGE_FOR_ACCIDENT + extraPerAccident + TOTAL_AMOUNT_TO_PAY + amount;
        return new ToPay(message, amount);
    }
}
