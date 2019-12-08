import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class InsuranceProgram {

    private static final String ENTER_YOUR_AGE = "Enter your age: ";
    private static final String ADDITIONAL_SURCHARGE = "Additional surcharge ";
    private static final String NO_ADDITIONAL_SURCHARGE = "No additional surcharge";
    private static final String PLEASE_ENTER_A_VALID_NUMBER = "That's not a valid number\nPlease enter a valid one";
    private static final String HOW_MANY_ACCIDENTS_DID_YOU_HAVE = "\nHow many accidents did you have?\n";
    private static final String NO_SURCHARGE = "No surcharge";
    private static final String ADDITIONAL_SURCHARGE_FOR_ACCIDENT = "Additional surcharge for accident(s): ";
    private static final String TOTAL_AMOUNT_TO_PAY = " \nTotal amount to pay: ";
    private static final String UNINSURABLE = "Not eligible for insurance cover";
    private static final int minimumAge = 16;
    private static final String NOT_INSURABLE_AGE_MESSAGE = "You should be at least " + minimumAge + " years old to insure your vehicle\nBye...";


    public static void main(String[] args) {
        int basicInsurance = 500;
        int surcharge = 100;

        System.out.print(ENTER_YOUR_AGE);
        int age = getUserInput();

        if (age < minimumAge) {
            System.out.println(NOT_INSURABLE_AGE_MESSAGE);
        } else if (age < 25) {
            String message = ADDITIONAL_SURCHARGE + surcharge;
            processInsurance(message, basicInsurance + surcharge);
        } else processInsurance(NO_ADDITIONAL_SURCHARGE, basicInsurance);
    }

    /**
     * Gets user input making sure is a valid number
     *
     * @return
     */
    private static int getUserInput() {
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            while (!sc.hasNextInt()) {
                System.out.println(PLEASE_ENTER_A_VALID_NUMBER);
                sc.next();
            }
            number = sc.nextInt();
        }
        while (number <= 0);
        return number;
    }


    /**
     * Process the data and returns the insurance's value
     *
     * @param message
     * @param basicInsurance
     */
    private static void processInsurance(String message, int basicInsurance) {
        printMessages(message);
        int accidents = getUserInput();
        getAmountToPay(basicInsurance, accidents);
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
     * Logic to the calculate the additional surcharge per accident
     *
     * @param basicInsurance
     * @param accidents
     */
   static int getAmountToPay(int basicInsurance, int accidents) {
        int cost;
        switch (accidents) {
            case 0:
                System.out.println(NO_SURCHARGE);
                System.out.println(TOTAL_AMOUNT_TO_PAY + basicInsurance);
                return basicInsurance;
            case 1:
                cost = basicInsurance + 50;
                System.out.println(ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 50 + TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 2:
                cost = basicInsurance + 125;
                System.out.println(ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 125 + TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 3:
                cost = basicInsurance + 225;
                System.out.println(ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 225 + TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 4:
                cost = basicInsurance + 375;
                System.out.println(ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 375 + TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 5:
                cost = basicInsurance + 575;
                System.out.println(ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 575 + TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            default:
                System.out.println(UNINSURABLE);
                return -1;
        }
    }
}

