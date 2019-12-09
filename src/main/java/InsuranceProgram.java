import java.util.HashMap;
import java.util.Scanner;

public class InsuranceProgram {

    private static Prices price = new Prices(500, 100);

    public static void main(String[] args) {
        System.out.print(Constants.ENTER_YOUR_AGE);
        int age = getUserInput();

        if (age < Constants.minimumAge) {
            System.out.println(Constants.NOT_INSURABLE_AGE_MESSAGE);
        } else if (age < 25) {
            price.setCurrentPrice(price.getUnder25Surcharge());
            processInsurance(Constants.ADDITIONAL_SURCHARGE + price.getBasicSurcharge());
        } else {
            price.setCurrentPrice(price.getBasicInsurance());
            processInsurance(Constants.NO_ADDITIONAL_SURCHARGE);
        }
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
     */
    private static void processInsurance(String message) {
        printMessages(message);
        int accidents = getUserInput();
        System.out.println(getMessageToDisplay(accidents).getMessage());
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


    public static ToPay getMessageToDisplay(int numberOfAccidents) {
        HashMap<Integer, ToPay> amount = AccidentSurcharge.getAmount(price);
        ToPay value = amount.get(numberOfAccidents);
        return value != null ? value : new ToPay(Constants.UNINSURABLE, -1);
    }
}

