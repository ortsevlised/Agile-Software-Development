import java.util.HashMap;

public class AccidentSurcharge {
    static HashMap<Integer, ToPay> getAmount(int currentPrice) {
        HashMap<Integer, ToPay> ACCIDENTS =
                new HashMap<>();
        {
            ACCIDENTS.put(0, new ToPay(Constants.NO_SURCHARGE + "\n" + Constants.TOTAL_AMOUNT_TO_PAY, currentPrice));
            ACCIDENTS.put(1, getValue(currentPrice, ExtraPerAccident.ONE_ACCIDENT.getExtraToPay()));
            ACCIDENTS.put(2, getValue(currentPrice, ExtraPerAccident.TWO_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(3, getValue(currentPrice, ExtraPerAccident.THREE_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(4, getValue(currentPrice, ExtraPerAccident.FOUR_ACCIDENTS.getExtraToPay()));
            ACCIDENTS.put(5, getValue(currentPrice, ExtraPerAccident.FIVE_ACCIDENTS.getExtraToPay()));
        }
        return ACCIDENTS;
    }

    private static ToPay getValue(int currentPrice, int extraPerAccident) {
        return new ToPay(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + extraPerAccident +
                Constants.TOTAL_AMOUNT_TO_PAY + (currentPrice + extraPerAccident), currentPrice + extraPerAccident);
    }
}
