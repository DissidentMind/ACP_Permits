package gui.desing.startup;
import java.util.Arrays;
import java.util.List;

public class DefaultsLoader {
    public static final String TITLE = "Administration Control Panel for Permits - Pipeline Projects";
    public static final String ERROR_DIALOG_TITLE = "Error";
    public static final String WARNING_DIALOG_TITLE = "Warning";
    public static final String INFO_DIALOG_TITLE = "Information";
    public static final String REGEX_DOC_NAMING = "([a-zA-Z]{4})-([a-zA-Z]{3,5})-([a-zA-Z]{3,5})-([0-9]{4})-([0-9a-zA-Z-]*)";
    //Validate Regex Filenet - Start with 1 with a length between 9-11 (Correct length 10 in order to avoid fail to conversion of similar length numbers
    public static final String REGEX_FILENET = "(((?:1)[0-9]{9,11}$)|([0-9]{1},[0-9]{3},[0-9]{3},[0-9]{3}))";
    public static final String REGEX_DATE_OK = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
    public static final String REGEX_DATE_ENG = "((January|Febuary|March|April|May|June|July|August|September|October|November|" +
            "December|Enero|Febrero|Marzo|Abril|Mayo|Junio|Julio|Agosto|Septiembre|Septiembrre|Saptiembre|Octubre|Octubr|Ocutubre|Noviembre|Diciembre|Decembere).([0-9]{1,2}).*(\\d{2,4}))";
    public static final String REGEX_DATE_RENG = "((\\d{4}).([0-9]{1,2}).*(January|Febuary|March|April|May|June|July|August|September|October" +
            "|November|December|Enero|Febrero|Marzo|Abril|Mayo|Junio|Julio|Agosto|Septiembre|Septiembrre|Octubre|Noviembre|Diciembre|Decembere))";
    public static final String REGEX_DATE_DDMMMMYY = "(([0-9]{1,2}).(January|Febuary|March|April|May|June|July|August|September|October|November|December|Enero|Febrero|Marzo|Abril|Mayo|Junio|Julio|Agosto|Septiembre|" +
            "Septiembrre|Octubre|Noviembre|Diciembre|Decembere|Ene|Jan|Feb|Mar|Abr|Apr|May|Jun|Jul|Aug|Ago|Sep|Oct|Nov|Dec|Dic).([0-9]{1,2}))";
    public static final String REGEX_DATE_DDMMYY = "((\\d{1,2})[-\\/](\\d{1,2})[-\\/](\\d{1,2}))";
    public static final List<String> DEFAULT_FILE_EXTENSIONS = Arrays.asList(".pdf", ".kmz", ".dwg", ".doc", ".docx", ".xls",".xlsx", ".xlsm", ".ppt", ".pps", ".pptx", ".xml", ".csv",
            ".zip",".7z", ".rar", ".sql", ".mdb", ".email", ".msg", ".rtf", ".txt");
    public static final List<String> DEFAULT_IMAGE_EXTENSIONS = Arrays.asList(".png", ".gif", ".jpg", ".jpeg", ".tiff");

    public static final String defaultDBName = "TGNH_TVDR_Permits";
    public static final String sqlSerClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String defaultDowPathFol = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\TGNH\\TVDR_Project\\PermisosTramosPendientes\\Files-Pendientes\\licencias";
    public static final String defaultLogsPath = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-paths_files-letters-inout-total_12Aug21.csv";
    public static final String defaultLog_TVDR = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-paths_files-letters-inout-total_12Aug21.csv";
    public static final String defaultLog_TOPO = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TOPO-totaldeliveries-permits-26Apr21.csv";
    public static final String defaultLog_TXTL = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TXTL-paths_files-letters-inout-total_29Jan20.csv";
    public static final String defaultLog_SDTT = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\SDTT-paths_files-letters-inout-total_24Nov20.csv";
    public static final String defaultDeadlinePath = "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-DumpVencimientos-Mar21.xlsx";

    private String customDBName = "";
    private String customDowPathFol = "";
    private String customDeadlinePath = "";

    public static String getTITLE() {
        return TITLE;
    }

    public static String getErrorDialogTitle() {
        return ERROR_DIALOG_TITLE;
    }

    public static String getWarningDialogTitle() {
        return WARNING_DIALOG_TITLE;
    }

    public static String getInfoDialogTitle() {
        return INFO_DIALOG_TITLE;
    }

    public static String getRegexDocNaming() {
        return REGEX_DOC_NAMING;
    }

    public static String getRegexFilenet() {
        return REGEX_FILENET;
    }

    public static String getRegexDateOk() {
        return REGEX_DATE_OK;
    }

    public static String getRegexDateEng() {
        return REGEX_DATE_ENG;
    }

    public static String getRegexDateReng() {
        return REGEX_DATE_RENG;
    }

    public static String getRegexDateDdmmmmyy() {
        return REGEX_DATE_DDMMMMYY;
    }

    public static String getRegexDateDdmmyy() {
        return REGEX_DATE_DDMMYY;
    }

    public static List<String> getDefaultFileExtensions() {
        return DEFAULT_FILE_EXTENSIONS;
    }

    public static List<String> getDefaultImageExtensions() {
        return DEFAULT_IMAGE_EXTENSIONS;
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

    public static String getDefaultLogsPath() {
        return defaultLogsPath;
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
}
