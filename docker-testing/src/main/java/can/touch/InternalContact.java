package can.touch;

class InternalContact {

    private final int customerId;
    private final String phoneNumber;

    public InternalContact(int customerId, String phoneNumber) {
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
