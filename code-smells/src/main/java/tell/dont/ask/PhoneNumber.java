package tell.dont.ask;

public class PhoneNumber {

    private final String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isValid() {
        return number.length() == 11;
    }

    public boolean isMobile() {
        return number.startsWith("07");
    }
}
