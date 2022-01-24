package gui.controller.init;

import utils.db.Db_Utility;
import utils.files.FileCSV_Utility;
import utils.files.FileSystem_Utility;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Class that generate first load for apps
 */
public class InitialStratupGui {
    InitialStratupGui() {
        System.out.println("Loading App...");
    }

    private int screenHeight;
    private int screenWidth;

    public static int getScreenHeight() {
        Dimension currentScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) currentScreenSize.getHeight();
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public static int getScreenWidth() {
        Dimension currentScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) currentScreenSize.getWidth();
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public static void loadingDBApp() {
        /*
        Validate connection if exist
        Required Path to Library (VM Argument): -Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\;${env_var:PATH}"
        Required Path to Library (VM Argument): -Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\sqljdbc_8.2\enu\auth\x64"
        Required Path to Library (VM Argument): -Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\sqljdbc_9.2\enu\auth\x64"
         */

        /*
        Verify if Database and Table Exist
         */
        if(Db_Utility.TestConnection_JDBC(VaultValuesLoader.getDefaultHost(), VaultValuesLoader.getJdbcPort(), VaultValuesLoader.getDefaultDBName(), VaultValuesLoader.getDefaultTable())){
            //TODO Update "Found Default DB Connection - Testing Connection - Table Exist - Connection Verified
            //TODO Load All Tables and Databases in Engine

        }else{
            JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(), "The connection to Database is not available. Verify.", "DB Verification Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        /*
        Load Default Dataset in CSV File - (TVDR)
         */
        System.out.println("Path: "+VaultValuesLoader.getDefaultLogCSVFilePath());

        if(FileSystem_Utility.fileExistInPath(VaultValuesLoader.getDefaultLogCSVFilePath())){
            try {
                System.out.println("Loading Items from CSV...");

                System.out.println("Path: "+VaultValuesLoader.getDefaultLogCSVFilePath());

                SettingsStat.setItemsInCsvFile(FileCSV_Utility.getRowStringFromCSVtoList(VaultValuesLoader.getDefaultLogCSVFilePath(), 0));
                System.out.println("Total Items in List: "+SettingsStat.getTotalLogsInCSVFile());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(), "Error Loading CSV Content in List", "Error in CSV File Reading Process", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            //TODO Update "Found Default CSV File  - CSV File Loaded -
        }else{
            JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(), "Default CSV file cannot be read or doesn't exist", "File is not available", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        /*
        Load Default Store Procedures - CSV or XML
         */

        /*
        Load Default Tables id Database - TVDR
         */
    }
}
