package org.ortsevlised;

import java.util.HashMap;
import java.util.Scanner;

import static org.ortsevlised.AccidentSurcharge.getAccidentSurcharge;
import static org.ortsevlised.Constants.*;
import static org.ortsevlised.ExtraPerAccident.MORE_ACCIDENTS;

public class InsuranceProgram {

    private static InsurancePrice insurance;

    public static void main(String[] args) throws Exception {
        insurance = new InsurancePrice(500, 100);
        getInsuranceToPay();
    }

    /**
     * Gets the user input making sure is a valid number
     */
    private static void getInsuranceToPay() throws TooManyRetriesException {
        System.out.print(ENTER_YOUR_AGE);
        int age = getUserInput();

        if (age < MINIMUM_AGE) {
            System.out.println(NOT_INSURABLE_AGE_MESSAGE);
        } else if (age < 25) {
            insurance.setCurrentPrice(insurance.getUnder25Surcharge());
            processInsurance(String.format("%s%d", ADDITIONAL_SURCHARGE, insurance.getBasicSurcharge()));
        } else {
            insurance.setCurrentPrice(insurance.getBasicInsurance());
            processInsurance(NO_ADDITIONAL_SURCHARGE);
        }
    }

    /**
     * Gets the user input making sure is a valid number
     */
    public static int getUserInput() throws TooManyRetriesException {
        int retries = 0;
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            while (!sc.hasNextInt() && retries < 50) {
                System.out.println(PLEASE_ENTER_A_VALID_NUMBER);
                sc.next();
                retries++;
            }
            if(retries>=50){
                throw new TooManyRetriesException("The number of retries is too high");
            }
            number = sc.nextInt();
        }
        while (number < 0);
        return number;
    }

    /**
     * Process the data and returns the total insurance's value
     */
    private static void processInsurance(String message) throws TooManyRetriesException {
        printMessages(message);
        int numberOfAccidents = getUserInput();
        ToPay totalToPay = addSurchargePerAccident(numberOfAccidents, insurance.getCurrentPrice());
        insurance.setCurrentPrice(totalToPay.getAmount());
        System.out.println(totalToPay.getMessage());
    }

    /**
     * Prints the message to the screen
     *
     * @param message
     */
    private static void printMessages(String message) {
        System.out.println(message);
        System.out.print(HOW_MANY_ACCIDENTS_DID_YOU_HAVE);
    }

    /**
     * Adds the surcharge according to the amount of accidents
     * returns a map with the message to display to the user and the amount to pay
     */
    public static ToPay addSurchargePerAccident(int numberOfAccidents, int currentPrice) {
        HashMap<Integer, ToPay> amount = getAccidentSurcharge(currentPrice);
        ToPay value = amount.get(numberOfAccidents);
        return value != null ? value : new ToPay(UNINSURABLE, MORE_ACCIDENTS.getExtraToPay());
    }
}

