package cannot.touch;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {

    private static final String NAME = "A Data Source";

    public static DataSource createPostgres() {
        PGPoolingDataSource existingDataSource = PGPoolingDataSource.getDataSource(NAME);

        if(existingDataSource == null) {
            return createNewDatasource();
        }

        return existingDataSource;
    }

    public static DataSource createMysql() {
        MysqlDataSource source = new MysqlDataSource();

        source.setServerName("localhost");
        source.setDatabaseName("mysql");
        source.setPortNumber(3306);
        source.setUser("palantir");
        source.setPassword("so_secret");

        return source;
    }

    private static DataSource createNewDatasource() {
        Jdbc3PoolingDataSource source = new Jdbc3PoolingDataSource();

        source.setDataSourceName(NAME);
        source.setServerName("localhost");
        source.setDatabaseName("postgres");
        source.setPortNumber(5432);
        source.setUser("postgres");
        source.setPassword("mysecretpassword");
        source.setMaxConnections(10);

        return source;
    }
}

