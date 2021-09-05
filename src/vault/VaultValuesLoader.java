package vault;

public class VaultValuesLoader {
    public static final String JDBC_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_POSTGRESS_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String DEFAULT_HOST = "localhost";
    public static int JDBC_PORT = 1433;
    public static String DEFAULT_DB = "TGNH_TVDR_Permits";
    public static String DEFAULT_TABLE = "BD_PERMITS";

    private String POSTGRES_DB_STR = "jdbc:postgresql://127.0.0.1:8000/";
    private String SQLSERVER_DB_STR = "jdbc:mysql://localhost:3306/";
    private String USER = "root";
    private String PASS = "";

    public static final String defaultDBName = "TGNH_TVDR_Permits";
    public static final String sqlSerClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String defaultDowPathFol = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\TGNH\\TVDR_Project\\PermisosTramosPendientes\\Files-Pendientes\\licencias";
    public static String defaultLogsPath = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-paths_files-letters-inout-total_12Aug21.csv";

    public static final String defaultLog_TVDR = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-paths_files-letters-inout-total_12Aug21.csv";
    public static final String defaultLog_TOPO = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TOPO-totaldeliveries-permits-26Apr21.csv";
    public static final String defaultLog_TXTL = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TXTL-paths_files-letters-inout-total_29Jan20.csv";
    public static final String defaultLog_SDTT = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\SDTT-paths_files-letters-inout-total_24Nov20.csv";
    public static final String defaultDeadlinePath = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-DumpVencimientos-Mar21.xlsx";

    public String getPOSTGRES_DB_STR(String dataBase) {
        return POSTGRES_DB_STR.concat(dataBase);
    }

    public void setPOSTGRES_DB_STR(String POSTGRES_DB_STR) {
        this.POSTGRES_DB_STR = POSTGRES_DB_STR;
    }

    public String getSQLSERVER_DB_STR(String dataBase) {
        return SQLSERVER_DB_STR.concat(dataBase);
    }

    /*
    Starting defaul values for the first staturp
     */
    private String customDBName = "";
    private String customDowPathFol = "";
    private String customDeadlinePath = "";

    public void setSQLSERVER_DB_STR(String SQLSERVER_DB_STR) {
        this.SQLSERVER_DB_STR = SQLSERVER_DB_STR;
    }

    public String getDEFAULT_DB() {
        return DEFAULT_DB;
    }

    public void setDEFAULT_DB(String DEFAULT_DB) {
        this.DEFAULT_DB = DEFAULT_DB;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public static String getDefaultDBName() {
        return defaultDBName;
    }

    public static String getSqlSerClass() {
        return sqlSerClass;
    }

    public static String getDefaultDowPathFol() {
        return defaultDowPathFol;
    }

    public String getDefaultLogsPath() {
        return defaultLogsPath;
    }

    public void setDefaultDowPathFol(String defaultDownloadPath) {
        this.defaultDowPathFol = defaultDownloadPath;
    }

    public void setDefaultLogsPath(String defaultLogPath) {
        this.defaultLogsPath = defaultLogPath;
    }

    public static String getDefaultLog_TVDR() {
        return defaultLog_TVDR;
    }

    public static String getDefaultLog_TOPO() {
        return defaultLog_TOPO;
    }

    public static String getDefaultLog_TXTL() {
        return defaultLog_TXTL;
    }

    public static String getDefaultLog_SDTT() {
        return defaultLog_SDTT;
    }

    public static String getDefaultDeadlinePath() {
        return defaultDeadlinePath;
    }

    public String getCustomDBName() {
        return customDBName;
    }

    public String getCustomDowPathFol() {
        return customDowPathFol;
    }

    public String getCustomDeadlinePath() {
        return customDeadlinePath;
    }

    public static String getDefaultHost() {
        return DEFAULT_HOST;
    }

    public static void setDefaultHost(String defaultHost) {
        DEFAULT_HOST = defaultHost;
    }

    public static int getJdbcPort() {
        return JDBC_PORT;
    }

    public static void setJdbcPort(int jdbcPort) {
        JDBC_PORT = jdbcPort;
    }

    public static String getDefaultTable() {
        return DEFAULT_TABLE;
    }

    public static void setDefaultTable(String defaultTable) {
        DEFAULT_TABLE = defaultTable;
    }

}
