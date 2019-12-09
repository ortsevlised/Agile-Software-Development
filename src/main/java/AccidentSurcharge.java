import java.util.HashMap;

public class AccidentSurcharge {
    static HashMap<Integer, ToPay> getAmount(Prices price) {
        HashMap<Integer, ToPay> ACCIDENTS =
                new HashMap<>();
        {
            ACCIDENTS.put(0, new ToPay(Constants.NO_SURCHARGE + "\n" + Constants.TOTAL_AMOUNT_TO_PAY, price.getCurrentPrice()));
            ACCIDENTS.put(1, getValue(price, 50));
            ACCIDENTS.put(2, getValue(price, 125));
            ACCIDENTS.put(3, getValue(price, 225));
            ACCIDENTS.put(4, getValue(price, 375));
            ACCIDENTS.put(5, getValue(price, 575));
        }
        return ACCIDENTS;
    }

    private static ToPay getValue(Prices price, int i) {
        return new ToPay(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + i + Constants.TOTAL_AMOUNT_TO_PAY + (price.getCurrentPrice() + i), price.getCurrentPrice() + i);
    }
}
