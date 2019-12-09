package org.ortsevlised;

public class Prices {
    private static int basicInsurance;
    private static int basicSurcharge;
    private static int currentPrice;

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int totalInsuranceCharge) {
        this.currentPrice = totalInsuranceCharge;
    }

    public Prices(int basicInsurance, int basicSurcharge) {
        this.basicInsurance = basicInsurance;
        this.basicSurcharge = basicSurcharge;
    }

    public Prices(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getUnder25Surcharge() {
        return basicInsurance + basicSurcharge;
    }


    public int getBasicInsurance() {
        return basicInsurance;
    }

    public void setBasicInsurance(int basicInsurance) {
        this.basicInsurance = basicInsurance;
    }

    public int getBasicSurcharge() {
        return basicSurcharge;
    }

    public void setBasicSurcharge(int basicSurcharge) {
        this.basicSurcharge = basicSurcharge;
    }


}
