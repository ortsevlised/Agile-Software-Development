package org.ortsevlised;

public class InsurancePrice {
    private static int basicInsurance;
    private static int basicSurcharge;
    private static int currentPrice;

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int totalInsuranceCharge) {
        this.currentPrice = totalInsuranceCharge;
    }

    public InsurancePrice(int basicInsurance, int basicSurcharge) {
        this.basicInsurance = basicInsurance;
        this.basicSurcharge = basicSurcharge;
    }

    public InsurancePrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getUnder25Surcharge() {
        return basicInsurance + basicSurcharge;
    }


    public int getBasicInsurance() {
        return basicInsurance;
    }

    public int getBasicSurcharge() {
        return basicSurcharge;
    }

}
