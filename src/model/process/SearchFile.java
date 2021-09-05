package model.process;

import model.app.custom.TestTableModel;
import utils.files.FileCSV_Utility;
import utils.regex.Regex_Utility;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchFile {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    List<String> listCommunications = new ArrayList<String>();

    public String getListCommunications(int indexGet) {
        return listCommunications.get(indexGet);
    }

    public void setListCurrentCommunication(List<String> lst) {
        this.listCommunications = lst;
    }

    public List<String> getListCurrentCommunication() {
        return this.listCommunications;
    }

    public SearchFile() {
        FileCSV_Utility getText = new FileCSV_Utility();
        try {
            this.setListCurrentCommunication(getText.getRowStringFromCSVtoList(VaultValuesLoader.getDefaultLog_TVDR(), 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runProcessByIdFile(String idFile, JTable jtResult) {
        System.out.println("Folio de Comunicación: ");
        System.out.println("Item: " + idFile);

        SearchFile bCtApp = new SearchFile();
        Map<Integer, String> map = Regex_Utility.getHashIfCoincidenceFound(bCtApp.getListCurrentCommunication(), idFile);

        if (map.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Results Found", "No Results", JOptionPane.ERROR_MESSAGE);
        } else {

            //ArrayList<ResultSearchItem> list = new ArrayList<ResultSearchItem>();

            for (Integer integer : map.keySet()) {
                System.out.println(integer + " > " + map.get(integer));

                //ResultSearchItem rSI = new ResultSearchItem(integer,map.get(integer),map.get(integer));
                //list.add(rSI);
            }
            //***********************************************************************
            jtResult.setModel(TestTableModel.toTableModel(map));
            //***********************************************************************

            //ResultSearchItem.addRowToJTable(jtResult,list);

            /*ProgressRenderer renderer = new ProgressRenderer(0, 100);
            renderer.setStringPainted(true); // show progress text
            jtResult.setDefaultRenderer(JProgressBar.class, renderer);

            jtResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtResult.setRowHeight( (int) renderer.getPreferredSize().getHeight());*/
        }

/*        try {
            System.out.println("# ID of FIle");
            //*****************************************
            //int idR = s.nextInt();
            int idR = 12233; //***********************
            System.out.println("Folder Destination: ");
            String idP = DefaultsLoader.getDefaultDowPathFol();
            //*****************************************

            Path path = Paths.get(DefaultsLoader.defaultDowPathFol.concat(idP+"\\"));

            if (Files.exists(path)) {
                System.out.println("Path: "+path);

                SearchFile bcFA = new SearchFile();
                bcFA.copyFileToDestination(idP,idR);

            }else{
                System.out.println("No Path Found - Create Folder? (s/n)");
                Scanner s = new Scanner(System.in);
                char idC = s.next(".").charAt(0);
                if(idC == 's'){
                    boolean success = (new File(""+path)).mkdirs();
                    if (success) {
                        System.out.println("Successful Folder Creation");
                        SearchFile bcFA = new SearchFile();
                        bcFA.copyFileToDestination(idP,idR);
                    }else{
                        System.out.println("Error in Folder Creation");
                        //main(new String[] {"a"});
                    }
                }else{
                    //main(new String[] {"a"});
                }
            }//end else
        } catch (Exception e) {
            System.out.println("\n");
            System.out.println("Fail Input Data Type");
        }*/
    }

    public void copyFileToDestination(String idP, int idR) {

        try {
            File temp = new File(this.getListCommunications(idR));
            System.out.println("Name File: " + temp.getName());

            String tempDestPath = VaultValuesLoader.defaultDowPathFol.concat(idP + "\\").concat(temp.getName());
            File dest = new File(tempDestPath);

            SearchFile.fileCopier(temp, dest);

            System.out.println();
            System.out.println("-------------------------Archivo Copiado---------------------------- ");
            System.out.println();

        } catch (NullPointerException e) {
            System.out.println("Error in file: " + e);
        }
        //main(new String[] {"a"});
    }

    public void creationOfFoldersFromListofCSVFile() {
        List<String> listCommunications = new ArrayList<String>();
        FileCSV_Utility getText = new FileCSV_Utility();

        try {
            listCommunications = getText.getRowStringFromCSVtoList(VaultValuesLoader.defaultLog_TVDR, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Size: " + listCommunications.size());

        for (int i = 0; i < listCommunications.size(); i++) {
            boolean success = (new File(VaultValuesLoader.defaultDowPathFol + listCommunications.get(i))).mkdir();
            if (success) {
                System.out.println("Directory: " + listCommunications.get(i) + " created");
            }
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static void fileCopier(File input, File output) {

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        try {
	        /*System.out.println("File name :"+input.getName());
	        System.out.println("Path: "+input.getPath());
	        System.out.println("Absolute path:" +input.getAbsolutePath());
	        System.out.println("Parent:"+input.getParent());
	        System.out.println("Exists :"+input.exists());*/
            if (input.exists()) {
                //System.out.println("Is writeable:"+input.canWrite());
                //System.out.println("Is readable"+input.canRead());
                //System.out.println("Is a directory:"+input.isDirectory());
                //System.out.println("File Size in bytes "+input.length());
            }

            copyFileUsingStream(input, output);
            //System.out.println(fCopier.fixSlash("C:\\Users\\evanf\\Documents\\Ebooks\\Profesional SharePoint Administration.pdf"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("Done");
    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }

    /* private void tableSelectionChanged() {
     *//* Unregister from receiving notifications
       from the last selected download. *//*
        if (selectedDownload != null)
            selectedDownload.deleteObserver(DownloadManager.this);

    *//* If not in the middle of clearing a download,
       set the selected download and register to
       receive notifications from it. *//*
        if (!clearing) {
            selectedDownload =
                    tableModel.getDownload(table.getSelectedRow());
            selectedDownload.addObserver(DownloadManager.this);
            updateButtons();
        }
    }*/

}
