package tell.dont.ask;

public class Patient {

    private final String email;
    private final PhoneNumber phoneNumber;

    public Patient(String email, PhoneNumber phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public boolean hasPhoneNumber() {
        return phoneNumber != null;
    }

    public boolean hasEmailAddress() {
        return email != null;
    }
}
