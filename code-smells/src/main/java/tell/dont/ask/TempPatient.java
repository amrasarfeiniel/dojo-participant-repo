package tell.dont.ask;

import java.util.Optional;

import org.immutables.value.Value;

@Value.Immutable
public abstract class TempPatient {
    public abstract Optional<String> getEmailAddress();
    public abstract Optional<String> getPhoneNumber();
}
