package gui.controller.init;

import javax.swing.*;

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

    private static JPanel currentParentPanel = null;
    private static boolean datasetResultNotNull = false;

    private static String currentSelectedCSV = "";

    public static JPanel getCurrentParentPanel() {
        return currentParentPanel;
    }

    public static void setCurrentParentPanel(JPanel currentParentPanel) {
        SettingsStat.currentParentPanel = currentParentPanel;
    }

    public static String getCurrentSelectedCSV() {
        return currentSelectedCSV;
    }

    public static void setCurrentSelectedCSV(String currentSelectedCSV) {
        SettingsStat.currentSelectedCSV = currentSelectedCSV;
    }

    public static boolean isDatasetResultNotNull() {
        return datasetResultNotNull;
    }
    public static void setDatasetResultNotNull(boolean datasetResultNotNull) {
        SettingsStat.datasetResultNotNull = datasetResultNotNull;
    }
    public static JPanel getCurrentPanel() {
        return currentParentPanel;
    }
    public static void setCurrentPanel(JPanel currentPanel) {
        SettingsStat.currentParentPanel = currentPanel;
    }
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
