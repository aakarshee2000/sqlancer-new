package sqlancer.mariadb;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import sqlancer.DBMSSpecificOptions;
import sqlancer.OracleFactory;
import sqlancer.common.oracle.TestOracle;
import sqlancer.mariadb.MariaDBOptions.MariaDBOracleFactory;
import sqlancer.mariadb.MariaDBProvider.MariaDBGlobalState;
import sqlancer.mariadb.oracle.MariaDBNoRECOracle;

@Parameters(separators = "=", commandDescription = "MariaDB (default port: 3306, default host: localhost)")
public class MariaDBOptions implements DBMSSpecificOptions<MariaDBOracleFactory> {

    @Parameter(names = "--oracle")
    public List<MariaDBOracleFactory> oracles = Arrays.asList(MariaDBOracleFactory.NOREC);

    public enum MariaDBOracleFactory implements OracleFactory<MariaDBGlobalState> {

        NOREC {

            @Override
            public TestOracle create(MariaDBGlobalState globalState) throws SQLException {
                return new MariaDBNoRECOracle(globalState);
            }

        }
    }

    @Override
    public List<MariaDBOracleFactory> getTestOracleFactory() {
        return oracles;
    }

}
