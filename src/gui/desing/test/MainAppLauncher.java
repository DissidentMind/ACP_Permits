package gui.desing.test;

import gui.desing.imgs.ImgsLoader;
import model.process.SearchFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class MainAppLauncher extends JFrame {
    private JPanel frameJPanelParent;
    private JTabbedPane tablPanel;
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
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JButton runSearch;
    private JTextField textField2;
    private JButton btnSelectBulkCsv;
    private JTextField textField3;
    private JButton btnSelectDest;
    private JPanel srcTab;
    private JPanel dwnTab;
    private JPanel csvTab;
    private JPanel bulkTab;
    private JPanel storeTab;
    private JPanel confTab;
    private JButton btnSetDowDestination;
    private JButton btnCancelDownload;
    private ImageIcon srcImg;

    ImgsLoader imgsLoader;

    public MainAppLauncher() {
        super("Permits Documents Downloader Manager - Ver. 1.0.0.2021");
        setContentPane(frameJPanelParent);
        createUIComponents();

        runSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Action Run");
                if(itemSearch_Txt.getText() != ""){
                    SearchFile.runProcessByIdFile(itemSearch_Txt.getText());
                }else{
                    JOptionPane.showMessageDialog(null, "Search Parameter is Null");
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
    }

    public static void main(String[] args) {
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
        JMenuItem fileExitMenuItem = new JMenuItem("Exit",KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actionExit();
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

        tablPanel.addTab("Search File", imgsLoader.getSearchIcon(), srcTab, null);
        tablPanel.addTab("Download Files", imgsLoader.getDowloIcon(), dwnTab, null);
        tablPanel.addTab("Bulk CSV/DB", imgsLoader.getBdIcon(), csvTab, null);
        tablPanel.addTab("Bulk Logs", imgsLoader.getOpenFolderIcon(), bulkTab, null);
        tablPanel.addTab("Store Procedures", imgsLoader.getInfoIcon(), storeTab, null);
        tablPanel.addTab("Config", imgsLoader.getConfgIcon(), confTab, null);

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

    }
}
