import org.junit.Assert;
import org.junit.Test;

import tell.dont.ask.PhoneNumber;


public class PhoneNumberShould extends Assert {

    private static final String INVALID_NUMBER = "not a phone number";
    private static final String NON_MOBILE_NUMBER = "12345678901";
    private static final String MOBILE_NUMBER = "07845678901";

    @Test
    public void properly_identify_invalid_number() {
        PhoneNumber number = new PhoneNumber(INVALID_NUMBER);
        assertFalse(number.isValid());
    }

    @Test
    public void properly_identify_valid_number() {
        PhoneNumber number = new PhoneNumber(MOBILE_NUMBER);
        assertTrue(number.isValid());
    }

    @Test
    public void return_provided_number_when_valid() {
        PhoneNumber number = new PhoneNumber(NON_MOBILE_NUMBER);
        assertEquals(NON_MOBILE_NUMBER, number.getNumber());
    }

    @Test
    public void properly_identify_mobile_number() {
        PhoneNumber number = new PhoneNumber(MOBILE_NUMBER);
        assertTrue(number.isMobile());
    }

    @Test
    public void properly_identify_non_mobile_number() {
        PhoneNumber number = new PhoneNumber(NON_MOBILE_NUMBER);
        assertFalse(number.isMobile());
    }
}
