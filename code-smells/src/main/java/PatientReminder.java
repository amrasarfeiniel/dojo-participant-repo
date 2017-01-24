import tell.dont.ask.EmailService;
import tell.dont.ask.Patient;
import tell.dont.ask.PhoneNumber;
import tell.dont.ask.TextMessageService;


public class PatientReminder {

    private final EmailService emailService;
    private final TextMessageService phoneService;

    public PatientReminder(EmailService emailService, TextMessageService phoneService) {
        this.emailService = emailService;
        this.phoneService = phoneService;
    }

    public void remind(Patient patient) {
        emailPatientIfTheyHaveAnEmailAddress(patient);
        remindPatientByPhoneIfTheyHaveAPhoneNumber(patient);
    }

    private void remindPatientByPhoneIfTheyHaveAPhoneNumber(Patient patient) {
        if(patient.hasPhoneNumber()) {
            textIfMobileNumberOtherwiseCall(patient.getPhoneNumber());
        }
    }

    private void textIfMobileNumberOtherwiseCall(PhoneNumber phoneNumber) {
        if(phoneNumber.isMobile()) {
            phoneService.sendTextReminderTo(phoneNumber.getNumber());
        } else {
            phoneService.callWithReminder(phoneNumber.getNumber());
        }
    }

    private void emailPatientIfTheyHaveAnEmailAddress(Patient patient) {
        String emailAddress = patient.getEmailAddress();

        // Email addresses are null when the patient doesn't have one
        if(emailAddress != null) {
            emailService.emailReminderTo(emailAddress);
        }
    }

    private boolean isMobileNumber(String phoneNumber) {
        return phoneNumber.startsWith("07");
    }

}