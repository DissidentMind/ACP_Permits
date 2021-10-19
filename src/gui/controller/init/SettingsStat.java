package gui.controller.init;

public class SettingsStat {
    private static boolean dbSearchActive = false;
    private static boolean csvSearchActive = false;
    private static boolean useAsDefaultDestLocation = false;
    private static String currentPathFolderDest = "";
    private static String currentPathCSVFile = "";
    private static String currentDBDest = "";
    private static String currentTableDBDest = "";
    private static String currentLogsDest = "";
    private static String currentStoreProcedure = "";

    public static boolean isDbSearchActive() {
        return dbSearchActive;
    }

    public static void setDbSearchActive(boolean dbSearchActive) {
        SettingsStat.dbSearchActive = dbSearchActive;
    }

    public static boolean isCsvSearchActive() {
        return csvSearchActive;
    }

    public static void setCsvSearchActive(boolean csvSearchActive) {
        SettingsStat.csvSearchActive = csvSearchActive;
    }

    public static String getCurrentPathFolderDest() {
        return currentPathFolderDest;
    }

    public static void setCurrentPathFolderDest(String currentPathFolderDest) {
        SettingsStat.currentPathFolderDest = currentPathFolderDest;
    }

    public static boolean isUseAsDefaultDestLocation() {
        return useAsDefaultDestLocation;
    }

    public static void setUseAsDefaultDestLocation(boolean useAsDefaultDestLocation) {
        SettingsStat.useAsDefaultDestLocation = useAsDefaultDestLocation;
    }

    public static String getCurrentPathCSVFile() {
        return currentPathCSVFile;
    }

    public static void setCurrentPathCSVFile(String currentPathCSVFile) {
        SettingsStat.currentPathCSVFile = currentPathCSVFile;
    }

    public static String getCurrentDBDest() {
        return currentDBDest;
    }

    public static void setCurrentDBDest(String currentDBDest) {
        SettingsStat.currentDBDest = currentDBDest;
    }

    public static String getCurrentTableDBDest() {
        return currentTableDBDest;
    }

    public static void setCurrentTableDBDest(String currentTableDBDest) {
        SettingsStat.currentTableDBDest = currentTableDBDest;
    }

    public static String getCurrentLogsDest() {
        return currentLogsDest;
    }

    public static void setCurrentLogsDest(String currentLogsDest) {
        SettingsStat.currentLogsDest = currentLogsDest;
    }

    public static String getCurrentStoreProcedure() {
        return currentStoreProcedure;
    }

    public static void setCurrentStoreProcedure(String currentStoreProcedure) {
        SettingsStat.currentStoreProcedure = currentStoreProcedure;
    }
}
