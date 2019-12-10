package org.ortsevlised;

public enum ExtraPerAccident {
    NO_ACCIDENT(0),
    ONE_ACCIDENT(50),
    TWO_ACCIDENTS(125),
    THREE_ACCIDENTS(225),
    FOUR_ACCIDENTS(375),
    FIVE_ACCIDENTS(575),
    MORE_ACCIDENTS(-1);

    int extraToPay;
    public int getExtraToPay() {
        return extraToPay;
    }
    ExtraPerAccident(int extraToPay) {
        this.extraToPay = extraToPay;
    }

}
