public class ToPay {
    private String message;
    private int amount;

    public ToPay(String message, int amount) {
        this.message = message;
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
