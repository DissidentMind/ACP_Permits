package gui.desing.test;

import gui.components.JTableTemplate_Download;
import gui.components.JTableTemplate_Search;
import gui.controller.init.InitialStratupGui;
import gui.controller.init.SettingsStat;
import gui.desing.imgs.ImgsLoader;

import model.process.Download;
//import model.app.custom.download.Download;

import model.process.Record;
import utils.choosers.FileChooser_Utility;
import utils.choosers.RunFileChooser;
import utils.custom.Verifier_Utility;
import utils.files.FileSystem_Utility;
import utils.regex.Regex_Utility;
import vault.VaultValuesLoader;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

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
    private JLabel infoSearch_Txt;

    public void setInfoSearch_Txt(String infoSearch_Txt) {
        this.infoSearch_Txt.setText(infoSearch_Txt);
    }

    public void updateAddButton(boolean flag)
    {
        if(flag){
            addButton.setBorderPainted(true);
            addButton.setEnabled(true);
        }else{
            addButton.setBorderPainted(false);
            addButton.setEnabled(false);
        }
    }

    public void updateClearButton(boolean flag)
    {
        if(flag){
            clearSelectButton.setBorderPainted(true);
            clearSelectButton.setEnabled(true);
        }else{
            clearSelectButton.setBorderPainted(false);
            clearSelectButton.setEnabled(false);
        }
    }

    public void updateProccessButton(boolean flag){
        if(flag){
            processDownloadsButton.setBorderPainted(true);
            processDownloadsButton.setEnabled(true);
        }else{
            processDownloadsButton.setBorderPainted(false);
            processDownloadsButton.setEnabled(false);
        }
    }

    private FileChooser_Utility chooserFile;
    private JTable srchResult_JTable;
    private JScrollPane srchScrollContainer;
    private JTable downloadTb_JTable;
    private Record selectedDownload;
    private Download itemToDownload;
    private ArrayList<Record> resultQuery;
    private JTableTemplate_Search tableModel;
    private TableColumnModel columnModel;
    private ArrayList<String> downloadList;

    private JTableTemplate_Download tableDownloadModel;
    private Verifier_Utility vUtils;
    //private InitialStratupGui initialStartup;

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

        updateAddButton(false);
        updateClearButton(false);
        updateProccessButton(false);

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

        processDownloadsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Display Selected Items");
                //Processing List to Download
                tableModel.processSelectedItems();
                downloadList = tableModel.getListSelectedItemsFound();
                tableModel.removeAllSelectedItemsFound();
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

                if (!FileSystem_Utility.folderExistInPath(currentDestFolderPath_Txt.getText())) {
                    JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(), "Destination Folder doesn´t exist", "File System Error", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Start Download...");
            }
        });

        clearSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Clear Selection");
                if(tableModel.getRowCount()!=0){
                    tableModel.removeAllRows();
                    tableModel.removeAllSelectedItemsFound();
                    itemSearch_Txt.setText("");
                }
                setInfoSearch_Txt("");
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
                if (useDefaultLocationCheckBox.getModel().isSelected()) {
                    String currentDefaultPath = VaultValuesLoader.getDefaultDowPathFol();
                    currentDestFolderPath_Txt.setEditable(false);
                    currentDestFolderPath_Txt.setText(currentDefaultPath);
                    SettingsStat.setCurrentPathFolderDest(currentDefaultPath);
                    //currentDestFolderPath_Txt.enable(false);

                } else {
                    currentDestFolderPath_Txt.setEditable(true);
                    currentDestFolderPath_Txt.setText("");
                    SettingsStat.setCurrentPathFolderDest(currentDestFolderPath_Txt.getText());
                    //currentDestFolderPath_Txt.enable(true);
                }
                //SettingsStat.setUseAsDefaultDestLocation(useDefaultLocationCheckBox.getModel().isSelected());
                System.out.println("Check:" + useDefaultLocationCheckBox.getModel().isSelected());
                System.out.println("Path: " + SettingsStat.getCurrentPathFolderDest());
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
                });
            }
        });
        defaultDest_CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (defaultDest_CheckBox.getModel().isSelected()) {
                    String currentLogPath = VaultValuesLoader.getDefaultLogsPath();
                    logsFolderPath_Txt.setEditable(false);
                    logsFolderPath_Txt.setText(currentLogPath);
                    SettingsStat.setCurrentLogsDest(currentLogPath);
                } else {
                    logsFolderPath_Txt.setEditable(true);
                    logsFolderPath_Txt.setText("");
                    SettingsStat.setCurrentLogsDest(logsFolderPath_Txt.getText());
                }
            }
        });
        /*
        Action Button - Add New Record to Download List
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableModel.getRowCount()>0){
                    for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
                        if((Boolean) tableModel.getValueAt(i, 3)){
                            //addDownloadTabs(tableModel.getValueAt(i,1).toString());
                            System.out.println("Vale: "+tableModel.getValueAt(i,2));
                            //tableDownloadModel.addDownload(new Download((String) tableModel.getValueAt(i,2)));



                            tableModel.removeRow(i);
                        }
                    }
                }
                System.out.println("Total: "+tableDownloadModel.getRowCount());
                downloadTb_JTable.setModel(tableDownloadModel);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SplashLoaderLauncher dialog = new SplashLoaderLauncher();
            dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.setTitle("Loading... ");
            dialog.setSize(new Dimension((int)Math.round(InitialStratupGui.getScreenWidth()*.41),(int)Math.round(InitialStratupGui.getScreenHeight()*.36)));
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

    public void actionAdd() {
        System.out.println("Action Run");
        if (!itemSearch_Txt.getText().equals("")) {
            resultQuery = getArrayListResultsIfCoincidenceFound((ArrayList<String>) SettingsStat.getItemsInCsvFile(), itemSearch_Txt.getText());
            System.out.println("Query Size: " + resultQuery.size());
            setInfoSearch_Txt("Query Result Size: "+resultQuery.size());

            if(resultQuery.size()>0){
                updateAddButton(true);
                updateClearButton(true);
                updateProccessButton(true);
                /*New table model for display result data set*/
                /*------------------------------------------------*/
                tableModel = new JTableTemplate_Search();
                /*------------------------------------------------*/
                /*New table model Created when exist a result in current execution*/
                /*------------------------------------------------*/
                tableDownloadModel = new JTableTemplate_Download();
                /*------------------------------------------------*/

                tableModel.setListItemsFound(resultQuery);

                for(Record record: tableModel.getListItemsFound()){
                    System.out.println("Id: " + record.indexId);
                    System.out.println("File: " + record.fileName);
                    System.out.println("Path: " + record.filePath);
                    System.out.println("Flag: " + record.selectedId);
                }

                srchResult_JTable.setModel(tableModel);
                columnModel = srchResult_JTable.getColumnModel();
                /*columnModel.getColumn(0).setPreferredWidth(8);
                columnModel.getColumn(1).setPreferredWidth(165);
                columnModel.getColumn(2).setPreferredWidth(1200);
                columnModel.getColumn(3).setPreferredWidth(12);*/

                columnModel.getColumn(0).setPreferredWidth((int)Math.round(srchResult_JTable.getWidth()*.05));
                columnModel.getColumn(1).setPreferredWidth((int)Math.round(srchResult_JTable.getWidth()*.15));
                columnModel.getColumn(2).setPreferredWidth((int)Math.round(srchResult_JTable.getWidth()*.75));
                columnModel.getColumn(3).setPreferredWidth((int)Math.round(srchResult_JTable.getWidth()*.05));


            }else {
                //tableModel.removeAllRows();
                updateAddButton(false);
                updateClearButton(false);
                updateProccessButton(false);

                JOptionPane.showMessageDialog(SettingsStat.getCurrentParentPanel(),
                        "Non Items Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
                setInfoSearch_Txt("Non matches were found");
            }
        } else {
            updateAddButton(false);
            updateClearButton(false);
            updateProccessButton(false);

            JOptionPane.showMessageDialog(SettingsStat.getCurrentParentPanel(),
                    "Empty Input Field", "Error",
                    JOptionPane.ERROR_MESSAGE);
            setInfoSearch_Txt("Empty search field");
        }
    }
    /**
     * Function that returns a Array List of Records that contains index, filename, path of the inputList that contains
     * the parameter searched.
     *
     * @param inputList
     * @param inputSearchParam
     * @return
     */
    public static ArrayList<Record> getArrayListResultsIfCoincidenceFound(ArrayList<String> inputList, String inputSearchParam) {
        ArrayList<Record> rcdS = new ArrayList<Record>();
        Regex_Utility frU = new Regex_Utility();
        for (int i = 0; i < inputList.size(); i++) {
            if (frU.findCurrentIncidenteInString(inputList.get(i), inputSearchParam) != null) {
                File tmp = new File(inputList.get(i));
                Record rd = new Record(i, tmp.getName(), tmp.getAbsolutePath(),false);
                rcdS.add(rd);
            }
        }
        return rcdS;
    }

    /**
     * Function that add new download item for the download tab
     */
    /*private void addDownloadTabs(String path) {
        if (Verifier_Utility.intranetVerifyUrl(path)) {
            tableDownloadModel.addDownload(new Download(path));
            setInfoSearch_Txt("Processing: "+path); // reset add text field
        } else {
            JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(),
                    "Invalid Download URL", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }*/
}