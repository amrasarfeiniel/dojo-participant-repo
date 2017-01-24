package can.touch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ContactListMapper implements ResultSetMapper<ContactDetail> {
    @Override
    public ContactDetail map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new ContactDetail(r.getString("name"), r.getString("phonenumber"));
    }

}
