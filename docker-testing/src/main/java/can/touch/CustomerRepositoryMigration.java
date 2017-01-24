package can.touch;

public class CustomerRepositoryMigration {

    public static void migrate(CustomerRepository from, CustomerRepository to) {
        to.createCustomerTable();
        to.createPhoneNumberTable();
        for (Customer customer : from.getAllCustomers()) {
            to.insertCustomer(customer.getId(), customer.getName());
        }
        for (InternalContact contact : from.getAllContactsInternal()) {
            to.insertContactDetail(contact.getCustomerId(), contact.getPhoneNumber());
        }
    }
}
