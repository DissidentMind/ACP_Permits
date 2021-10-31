package model.process;

import gui.controller.init.SettingsStat;
import utils.files.FileCSV_Utility;
import utils.regex.Regex_Utility;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchFile {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    /**
     * Function that retrieve search results in Map <Index, FileName>
     * @param paramToSearch
     * @return
     */
    public Map<Integer, String> getSearchResultMap(String paramToSearch){
        /* Map Results*/
        Map<Integer, String> map = Regex_Utility.getHashIfCoincidenceFound(SettingsStat.getItemsInCsvFile(), paramToSearch);
        System.out.println("Map Result Size: "+map.size());

        if (map.isEmpty()) {
            JOptionPane.showMessageDialog(SettingsStat.getCurrentPanel(), "Non Items Availables in List to Complete Search", "Input Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Iterator<Integer> mapIterator = map.keySet().iterator();
            while (mapIterator.hasNext()) {
                int key=mapIterator.next();
                System.out.println(key+" > "+map.get(key));
            }
        }
        return map;
    }

    /**
     * Functino that retrieves search results in an Array List of Records
     * @param paramToSearch
     * @return
     */
    public ArrayList<Record> getSearchResultList(String paramToSearch){
        /* Array List */
        ArrayList<Record> resultsList = Regex_Utility.getArrayListResultsIfCoincidenceFound(SettingsStat.getItemsInCsvFile(), paramToSearch);
        System.out.println("List Result Size: "+resultsList.size());
        return resultsList;
    }


    public void copyFileToDestination(String idP, int idR) {
        try {
            File temp = new File(SettingsStat.getItemsInCsvFileByIndexId(idR));
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
    }

    public void creationOfFoldersFromListofCSVFile() {
        List<String> listCommunications = new ArrayList<String>();
        FileCSV_Utility getText = new FileCSV_Utility();

        try {
            listCommunications = FileCSV_Utility.getRowStringFromCSVtoList(VaultValuesLoader.defaultLog_TVDR, 0);
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
                /*
                System.out.println("Is writeable:"+input.canWrite());
                System.out.println("Is readable"+input.canRead());
                System.out.println("Is a directory:"+input.isDirectory());
                System.out.println("File Size in bytes "+input.length());
                */
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
