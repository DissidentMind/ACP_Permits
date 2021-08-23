package gui.desing.startup;

public class DefaultsLoader {
    private final String defaultDBName = "TGNH_TVDR_Permits";
    private final String sqlSerClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String defaultDowPathFol = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\TGNH\\TVDR_Project\\PermisosTramosPendientes\\Files-Pendientes\\licencias";
    private final String defaultLogsPath = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-paths_files-letters-inout-total_12Aug21.csv";
    private final String defaultLog_TVDR = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-paths_files-letters-inout-total_12Aug21.csv";
    private final String defaultLog_TOPO = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TOPO-totaldeliveries-permits-26Apr21.csv";
    private final String defaultLog_TXTL = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TXTL-paths_files-letters-inout-total_29Jan20.csv";
    private final String defaultLog_SDTT = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\SDTT-paths_files-letters-inout-total_24Nov20.csv";
    private final String defaultDeadlinePath = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-DumpVencimientos-Mar21.xlsx";

    private final String customDBName = "";
    private final String customDowPathFol = "";
    private final String customDeadlinePath = "";

    public String getDefaultDBName() {
        return defaultDBName;
    }

    public String getSqlSerClass() {
        return sqlSerClass;
    }

    public String getDefaultDowPathFol() {
        return defaultDowPathFol;
    }

    public String getDefaultLogsPath() {
        return defaultLogsPath;
    }

    public String getDefaultLog_TVDR() {
        return defaultLog_TVDR;
    }

    public String getDefaultLog_TOPO() {
        return defaultLog_TOPO;
    }

    public String getDefaultLog_TXTL() {
        return defaultLog_TXTL;
    }

    public String getDefaultLog_SDTT() {
        return defaultLog_SDTT;
    }

    public String getDefaultDeadlinePath() {
        return defaultDeadlinePath;
    }
}
