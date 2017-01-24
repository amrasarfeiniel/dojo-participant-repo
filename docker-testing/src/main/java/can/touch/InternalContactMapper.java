package can.touch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class InternalContactMapper implements ResultSetMapper<InternalContact> {
    @Override
    public InternalContact map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new InternalContact(r.getInt("customer_id"), r.getString("phonenumber"));
    }

}
