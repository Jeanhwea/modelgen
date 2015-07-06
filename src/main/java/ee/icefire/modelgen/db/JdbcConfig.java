package ee.icefire.modelgen.db;

public class JdbcConfig {

    protected String driverClassName;
    protected String jdbcUrl;
    protected String jdbcUser;
    protected String jdbcPassword;

    public JdbcConfig() {
        // noop
    }

    public JdbcConfig(String driverClassName, String jdbcUrl, String jdbcUser, String jdbcPassword) {
        this.driverClassName = driverClassName;
        this.jdbcUrl = jdbcUrl;
        this.jdbcUser = jdbcUser;
        this.jdbcPassword = jdbcPassword;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }
}
