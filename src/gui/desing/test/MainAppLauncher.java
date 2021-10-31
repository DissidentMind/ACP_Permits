package gui.desing.test;

import gui.controller.init.InitialStratupGui;
import gui.controller.init.SettingsStat;
import gui.desing.imgs.ImgsLoader;
import gui.render.ProgressRenderer;
import model.app.Download;
import model.process.Record;
import model.process.SearchRecords_Model;
import utils.choosers.FileChooser_Utility;
import utils.choosers.RunFileChooser;
import model.process.SearchFile;
import utils.files.FileSystem_Utility;
import utils.regex.Regex_Utility;
import vault.VaultValuesLoader;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainAppLauncher extends JFrame {
    ImgsLoader imgsLoader;
    private JPanel frameJPanelParent;
    private JTabbedPane tabbedPTabs;
    private JToggleButton btnDBSearch;
    private JToggleButton btnCSVSearch;
    private JButton searchButton;
    private JButton btnStartDownload;
    private JButton clearSelectButton;
    private JButton processDownloadsButton;
    private JButton addButton;
    private JProgressBar progressBar3;
    private JTextField itemSearch_Txt;
    private JCheckBox useDefaultLocationCheckBox;
    private JButton btnExploreCSV;
    private JComboBox selectDb_List;
    private JComboBox selectTable_List;
    private JTextArea textArea1;
    private JButton runSearch;
    private JTextField csvFilePath_Txt;
    private JButton btnSelectBulkCsv;
    private JTextField logsFolderPath_Txt;
    private JButton btnSelectDest;
    private JPanel srcTab;
    private JPanel dwnTab;
    private JPanel csvTab;
    private JPanel bulkTab;
    private JPanel storeTab;
    private JPanel confTab;
    private JButton btnSetDowDestination;
    private JButton btnCancelDownload;
    private JPanel strProTab;
    private JButton btnStoreProcedure;
    private JComboBox selectStoreProc_List;
    private JButton addNewProcedureButton;
    private JCheckBox defaultDest_CheckBox;
    private JTextField currentDestFolderPath_Txt;
    private JTextField selectedCSVFile_Txt;
    private ImageIcon srcImg;
    private JDialog jDial;
    public JTable srchResult_JTable;
    private FileChooser_Utility chooserFile;

    private Record selectedDownload;
    private ArrayList<Record> resultQuery;
    private SearchRecords_Model rModel;

    public MainAppLauncher() {
        super("Permits Documents Downloader Manager - Ver. 1.0.0.2021");
        /*
        Starting App
         */
        setContentPane(frameJPanelParent);
        createUIComponents();
        InitialStratupGui.loadingDBApp();

        //Set Default JPanel Parent to set at middle the messages
        SettingsStat.setCurrentPanel(frameJPanelParent);
        /*
        Endig Init Validations
         */
        runSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                actionAdd();

            }
        });

        btnDBSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("BD Search");
                SettingsStat.setDbSearchActive(btnDBSearch.getModel().isSelected());
            }
        });

        btnCSVSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("CSV Search");
                SettingsStat.setCsvSearchActive(btnCSVSearch.getModel().isSelected());
            }
        });

        btnStartDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Exist: "+ FileSystem_Utility.fileExistInPath(SettingsStat.getCurrentPathFolderDest()));
                //createDirectoryAndGetPath
                /*
                1. Verificar si el path es correcto
                2. Verificar si existe el path
                   OK
                   Procede
                   ELSE
                   Notifación de Error
                 */

                if(!FileSystem_Utility.folderExistInPath(currentDestFolderPath_Txt.getText())){
                    JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(), "Destination Folder doesn´t exist", "File System Error", JOptionPane.ERROR_MESSAGE);
                }

                System.out.println("Start Download...");
            }
        });

        clearSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        btnSetDowDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwingUtilities.invokeLater(() -> {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    System.out.println("File Chooser Test - Download");
                    //System.out.println("Download Path Dest: "+RunFileChooser.createAndShowGUI(1));
                    currentDestFolderPath_Txt.setText(RunFileChooser.createAndShowGUI(1));
                });
            }
        });

        clearSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //srchResult_JTable.removeAll();
            }
        });

        btnStoreProcedure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        useDefaultLocationCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                /*
                Si está en false (default) se puede editar cualquier dirección
                Si esta en true carga por default la ubicación por default en la configuración.
                 */

                if(useDefaultLocationCheckBox.getModel().isSelected())
                {
                    String currentDefaultPath = VaultValuesLoader.getDefaultDowPathFol();
                    currentDestFolderPath_Txt.setEditable(false);
                    currentDestFolderPath_Txt.setText(currentDefaultPath);
                    SettingsStat.setCurrentPathFolderDest(currentDefaultPath);
                    //currentDestFolderPath_Txt.enable(false);

                }else{
                    currentDestFolderPath_Txt.setEditable(true);
                    currentDestFolderPath_Txt.setText("");
                    SettingsStat.setCurrentPathFolderDest(currentDestFolderPath_Txt.getText());
                    //currentDestFolderPath_Txt.enable(true);
                }

                //SettingsStat.setUseAsDefaultDestLocation(useDefaultLocationCheckBox.getModel().isSelected());
                System.out.println("Check:"+useDefaultLocationCheckBox.getModel().isSelected());
                System.out.println("Path: "+SettingsStat.getCurrentPathFolderDest());
            }
        });
        btnSelectBulkCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Bulk Select");

                SwingUtilities.invokeLater(() -> {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    System.out.println("File Chooser Test - Bulk CSV");
                    //System.out.println("Bulk Path: "+RunFileChooser.createAndShowGUI(0));
                    csvFilePath_Txt.setText(RunFileChooser.createAndShowGUI(0));
                });
            }
        });
        btnSelectDest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Bulk Select");

                SwingUtilities.invokeLater(() -> {
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    System.out.println("File Chooser Test - CSV Destination");
                    //System.out.println("Destination Path: "+RunFileChooser.createAndShowGUI(1));
                    logsFolderPath_Txt.setText(RunFileChooser.createAndShowGUI(1));
                });
            }
        });
        btnExploreCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("CSV Explorer Select");

                SwingUtilities.invokeLater(() -> {

                    String newPathCsvFile = RunFileChooser.createAndShowGUI(0);
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    //Parameter as 2: Folder Chooser
                    //FileChooser_Utility.createAndShowGUI(1);
                    System.out.println("File Chooser Test - Find CSV");
                    //RunFileChooser.createAndShowGUI(0);
                    //System.out.println("Path Explorer CSV: "+RunFileChooser.createAndShowGUI(0));

                    selectedCSVFile_Txt.setText(newPathCsvFile);

                    //selectedCSVFile_Txt.setText(RunFileChooser.createAndShowGUI(0));
                });
            }
        });
        defaultDest_CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(defaultDest_CheckBox.getModel().isSelected())
                {
                    String currentLogPath = VaultValuesLoader.getDefaultLogsPath();
                    logsFolderPath_Txt.setEditable(false);
                    logsFolderPath_Txt.setText(currentLogPath);
                    SettingsStat.setCurrentLogsDest(currentLogPath);
                }else{
                    logsFolderPath_Txt.setEditable(true);
                    logsFolderPath_Txt.setText("");
                    SettingsStat.setCurrentLogsDest(logsFolderPath_Txt.getText());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashLoaderLauncher dialog = new SplashLoaderLauncher();
            dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.setTitle("Loading... ");
            dialog.setSize(560, 280);
            dialog.setLocationRelativeTo(null);
            dialog.updateProgressBar();
            }
        );

        JFrame j = new JFrame("MainAppLauncher");
        j.setContentPane(new MainAppLauncher().frameJPanelParent);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);
        j.pack();
    }

    private void createUIComponents() {
        imgsLoader = new ImgsLoader();
        //Menu Declaration
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);

        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        runSearch.setFocusable(true);
        runSearch.setIcon(imgsLoader.getExecIcon());
        runSearch.setFocusPainted(true);
        runSearch.setForeground(new Color(0, 76, 153));
        runSearch.setFont(new Font("Tahoma", Font.BOLD, 12));

        srcTab.setFocusable(true);
        srcTab.setFont(new Font("Tahoma", Font.BOLD, 12));

        tabbedPTabs.addTab("Search File", imgsLoader.getSearchIcon(), srcTab, null);
        tabbedPTabs.addTab("Download Files", imgsLoader.getDowloIcon(), dwnTab, null);
        tabbedPTabs.addTab("Bulk CSV/DB", imgsLoader.getBdIcon(), csvTab, null);
        tabbedPTabs.addTab("Bulk Logs", imgsLoader.getOpenFolderIcon(), bulkTab, null);
        tabbedPTabs.addTab("Store Procedures", imgsLoader.getInfoIcon(), strProTab, null);
        tabbedPTabs.addTab("Config", imgsLoader.getConfgIcon(), confTab, null);

        addButton.setIcon(imgsLoader.getAddIcon());
        clearSelectButton.setIcon(imgsLoader.getBinIcon());
        processDownloadsButton.setIcon(imgsLoader.getProccDowIcon());

        btnCSVSearch.setIcon(imgsLoader.getCsvIcon());
        btnDBSearch.setIcon(imgsLoader.getServerDbIcon());

        btnSetDowDestination.setIcon(imgsLoader.getOpenFolderIcon());
        btnSetDowDestination.setForeground(new Color(0, 76, 153));
        btnSetDowDestination.setFocusable(true);
        btnSetDowDestination.setFocusPainted(true);

        btnCancelDownload.setIcon(imgsLoader.getRemoveIcon());
        btnStartDownload.setIcon(imgsLoader.getDownLoadIcon());

        btnExploreCSV.setIcon(imgsLoader.getOpenFolderIcon());

        btnSelectDest.setIcon(imgsLoader.getSelectFolder16Ico());
        btnSelectBulkCsv.setIcon(imgsLoader.getSelect16Ico());

        btnStoreProcedure.setIcon(imgsLoader.getExecIcon());
        addNewProcedureButton.setIcon(imgsLoader.getAddIcon());

    }

     public void actionAdd(){
        System.out.println("Action Run");
        if (!itemSearch_Txt.getText().equals("")) {

            srchResult_JTable.removeAll();

            rModel = new SearchRecords_Model();

            resultQuery = getArrayListResultsIfCoincidenceFound((List) SettingsStat.getItemsInCsvFile(),itemSearch_Txt.getText());
            System.out.println("Query Size: "+resultQuery.size());

            for (int i = 0; i < resultQuery.size(); i++) {
                System.out.println("Id: "+resultQuery.get(i).indexId);
                System.out.println("File: "+resultQuery.get(i).fileName);
                System.out.println("Path: "+resultQuery.get(i).filePath);
                rModel.addNewMatchResult(new Record(resultQuery.get(i).indexId, resultQuery.get(i).fileName,resultQuery.get(i).filePath));
            }

            srchResult_JTable.setModel(rModel);

            srchResult_JTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            srchResult_JTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            srchResult_JTable.getColumnModel().getColumn(2).setPreferredWidth(650);

            //table = new JTable(rModel);
            itemSearch_Txt.setText("");
            //table.setModel(rModel);
        }else{
            JOptionPane.showMessageDialog(null,
                    "Empty Input Field", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Record> getArrayListResultsIfCoincidenceFound(List inputList, String inputSearchParam){
        ArrayList<Record> rcdS = new ArrayList<Record>();
        Regex_Utility frU = new Regex_Utility();
        for (int i = 0; i < inputList.getItemCount(); i++) {
            if(frU.findCurrentIncidenteInString(inputList.getItem(i), inputSearchParam)!=null){
                File tmp = new File(inputList.getItem(i));
                Record rd = new Record(i,tmp.getName(),tmp.getAbsolutePath());
                rcdS.add(rd);
            }
        }
        return rcdS;
    }
}
