import java.util.Scanner;

public class InsuranceProgram {


    public static void main(String[] args) {
        int basicInsurance = 500;
        int surcharge = 100;

        System.out.print(Constants.ENTER_YOUR_AGE);
        int age = getUserInput();

        if (age < Constants.minimumAge) {
            System.out.println(Constants.NOT_INSURABLE_AGE_MESSAGE);
        } else if (age < 25) {
            String message = Constants.ADDITIONAL_SURCHARGE + surcharge;
            processInsurance(message, basicInsurance + surcharge);
        } else processInsurance(Constants.NO_ADDITIONAL_SURCHARGE, basicInsurance);
    }

    /**
     * Gets user input making sure is a valid number
     *
     * @return
     */
     static int getUserInput() {
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            while (!sc.hasNextInt()) {
                System.out.println(Constants.PLEASE_ENTER_A_VALID_NUMBER);
                sc.next();
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
        System.out.print(Constants.HOW_MANY_ACCIDENTS_DID_YOU_HAVE);
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
                System.out.println(Constants.NO_SURCHARGE);
                System.out.println(Constants.TOTAL_AMOUNT_TO_PAY + basicInsurance);
                return basicInsurance;
            case 1:
                cost = basicInsurance + 50;
                System.out.println(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 50 + Constants.TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 2:
                cost = basicInsurance + 125;
                System.out.println(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 125 + Constants.TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 3:
                cost = basicInsurance + 225;
                System.out.println(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 225 + Constants.TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 4:
                cost = basicInsurance + 375;
                System.out.println(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 375 + Constants.TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            case 5:
                cost = basicInsurance + 575;
                System.out.println(Constants.ADDITIONAL_SURCHARGE_FOR_ACCIDENT + 575 + Constants.TOTAL_AMOUNT_TO_PAY + cost);
                return cost;
            default:
                System.out.println(Constants.UNINSURABLE);
                return -1;
        }
    }
}

