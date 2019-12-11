package org.ortsevlised;

import java.util.HashMap;
import java.util.Scanner;

import static org.ortsevlised.AccidentSurcharge.getAccidentSurcharge;
import static org.ortsevlised.Constants.*;
import static org.ortsevlised.ExtraPerAccident.MORE_ACCIDENTS;

public class InsuranceProgram {

    private static Prices price = new Prices(500, 100);

    public static void main(String[] args) throws Exception {
        System.out.print(ENTER_YOUR_AGE);
        int age = getUserInput();

        if (age < minimumAge) {
            System.out.println(NOT_INSURABLE_AGE_MESSAGE);
        } else if (age < 25) {
            price.setCurrentPrice(price.getUnder25Surcharge());
            processInsurance(String.format("%s%d", ADDITIONAL_SURCHARGE, price.getBasicSurcharge()));
        } else {
            price.setCurrentPrice(price.getBasicInsurance());
            processInsurance(NO_ADDITIONAL_SURCHARGE);
        }
    }


    /**
     * Gets user input making sure is a valid number
     *
     * @return
     */
    static int getUserInput() throws TooManyRetriesException {
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
     * Process the data and returns the insurance's value
     *
     * @param message
     */
    private static void processInsurance(String message) throws TooManyRetriesException {
        printMessages(message);
        int numberOfAccidents = getUserInput();
        ToPay insuranceToPay = getInsuranceToPay(numberOfAccidents, price.getCurrentPrice());
        price.setCurrentPrice(insuranceToPay.getAmount());
        System.out.println(insuranceToPay.getMessage());
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

    public static ToPay getInsuranceToPay(int numberOfAccidents, int currentPrice) {
        HashMap<Integer, ToPay> amount = getAccidentSurcharge(currentPrice);
        ToPay value = amount.get(numberOfAccidents);
        return value != null ? value : new ToPay(UNINSURABLE, MORE_ACCIDENTS.getExtraToPay());
    }
}

