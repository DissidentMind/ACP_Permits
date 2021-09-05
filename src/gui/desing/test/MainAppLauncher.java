package gui.desing.test;

import gui.controller.init.InitialStratupGui;
import gui.desing.imgs.ImgsLoader;
import model.process.SearchFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

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
    private JTextField csvFilePath_Chooser;
    private JButton btnSelectBulkCsv;
    private JTextField logsFilePath_Chooser;
    private JButton btnSelectDest;
    private JPanel srcTab;
    private JPanel dwnTab;
    private JPanel csvTab;
    private JPanel bulkTab;
    private JPanel storeTab;
    private JPanel confTab;
    private JButton btnSetDowDestination;
    private JButton btnCancelDownload;
    private JTable srchResult_JTable;
    private JPanel strProTab;
    private JButton btnStoreProcedure;
    private JComboBox selectStoreProc_List;
    private JButton addNewProcedureButton;
    private JCheckBox defaultDest_CheckBox;
    private ImageIcon srcImg;
    private JDialog jDial;

    public MainAppLauncher() {
        super("Permits Documents Downloader Manager - Ver. 1.0.0.2021");
        /*
        Starting App
         */
        setContentPane(frameJPanelParent);
        createUIComponents();
        InitialStratupGui.loadingDBApp();

        //Lambda expression equivalent to new Runnable with out override the run method
       /* SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SplashLoaderLauncher dialog = new SplashLoaderLauncher();
                dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);
                dialog.setTitle("Loading... ");
                dialog.setSize(560, 280);
                dialog.setLocationRelativeTo(null);
                dialog.updateProgressBar();
            }
        });*/

        //SplashLoaderLauncher splashL = new SplashLoaderLauncher();
        //jDial = new SplashLoaderLauncher();
        //jDial.setVisible(true);
        /*SplashLoaderLauncher dialog = new SplashLoaderLauncher();
        dialog.setSize(520,360);
        dialog.pack();
        dialog.setVisible(true);*/
        //System.exit(0);

        /*
        Endig Init Validations
         */
        runSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Action Run");
                if (!itemSearch_Txt.getText().equals("")) {
                    //Pass parameter to search and the object to fill
                    SearchFile.runProcessByIdFile(itemSearch_Txt.getText(), srchResult_JTable);
                } else {
                    JOptionPane.showMessageDialog(null, "Search Parameter is Empty", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDBSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("BD Search");
            }
        });

        btnCSVSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("CSV Search");
            }
        });

        btnStartDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Select Download Destination...");
                int returnVal = fc.showOpenDialog(frameJPanelParent);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println("Opening: " + file.getName());
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });
        clearSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //srchResult_JTable.removeAll();
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
}
