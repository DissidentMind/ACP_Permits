package utils.files;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileSystem_Utility {
    /**
     * Function that verify if file exist.
     *
     * @param {String} filePathString
     * @return {String} flagConfirm
     */
    public static Boolean fileExistInPath(String filePathString) {
        Boolean flagConfirm = false;
        File f = new File(filePathString);

        if (f.exists() && !f.isDirectory()) {
            flagConfirm = true;
        }
        return flagConfirm;
    }

    /**
     * Function that copy files.
     *
     * @param {File} input
     * @param {File} output
     */
    public static void fileCopier(File input, File output) {
        try {
            if (input.exists() && output.exists()) {
                copyFileUsingStream(input, output);
            } else JOptionPane.showMessageDialog(null, "File dont exist - Input and/or output");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that copy file from location to other destination. Generate two temp files to another temp file calling a file Copier function.
     *
     * @param {String} tempFile
     * @param {String} destPath
     */
    public void generateCopyFileInPath(String tempFile, String destPath) {
        try {
            File temp = new File(tempFile);
            destPath = destPath.concat(temp.getName());
            File dest = new File(destPath);
            fileCopier(temp, dest);
        } catch (NullPointerException e) {
            System.out.println("Error in file: " + e);
        }

    }

    /**
     * Function that copy file using stream
     *
     * @param {File} source
     * @param {File} dest
     * @throws IOException
     */
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

    /**
     * Function that create a directory and return the path
     *
     * @param {String} destDir Directory name
     * @param {String} pathMain Path destination
     * @return {String}
     * @throws IOException
     */
    public String createDirectoryAndGetPath(String destDir, String pathMain) throws IOException {
        String tempStr = null;
        try {
            File directory = new File(pathMain.concat(String.valueOf(destDir)));
            if (!directory.exists()) {
                directory.mkdir();
                tempStr = pathMain.concat(directory.getName());
            }
        } catch (Exception e) {

        }
        return tempStr;
    }

    /**
     * Function that creates a Directory
     *
     * @param {String} destDir Path and name of the new folder
     * @throws IOException
     */
    public void createDirectory(String destDir) throws IOException {
        File directory = new File(String.valueOf(destDir));
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Copy Entire Contents of File as String
     *
     * @param {String} pathOutputFile Path of destination file
     * @param {String} strToBeSaved Data to be saved - required string
     */
    public void writeToFile(String pathOutputFile, String strToBeSaved) {
        try {
            FileOutputStream outputStream = new FileOutputStream(pathOutputFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(strToBeSaved);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy and Append Single String in Existing File
     *
     * @param {String} pathOutputFile Destination file path
     * @param {String} sstrToBeSaved String to be appended to the current file
     */
    public void writeToFileApend(String pathOutputFile, String sstrToBeSaved) {
        try {
            Files.write(Paths.get(pathOutputFile), sstrToBeSaved.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
        }
    }

    /**
     * Function that display files description in given path
     *
     * @param {String} path
     */
    public static void getFilesinFolder(String path) {
        try {
            File directoryPath = new File(path);
            FileFilter textFilefilter = new FileFilter() {
                public boolean accept(File file) {
                    boolean isFile = file.isFile();
                    if (isFile) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            //List of all the text files
            File filesList[] = directoryPath.listFiles(textFilefilter);
            System.out.println("List of the text files in the specified directory:");
            for (File file : filesList) {
                System.out.println("File name: " + file.getName());
                System.out.println("File path: " + file.getAbsolutePath());
                System.out.println("Size :" + file.getTotalSpace());
                System.out.println(" ");
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("File Error: " + e.getMessage());
        }
    }

    /**
     * Function that return a List<String> that contains the absolute path of given path
     *
     * @param {String} path
     * @return
     */
    public static List<String> getFileListInFolder(String path) {
        File directoryPath = new File(path);
        File filesList[] = directoryPath.listFiles();
        List<String> fileLst = new ArrayList<String>();

        for (File file : filesList) {
            fileLst.add(file.getAbsolutePath());
        }
        return fileLst;
    }

    /**
     * Function that return a List<String> that contains the absolute path of given path by file extension
     *
     * @param {String} path
     * @param {String} extension
     * @return
     */
    public static List<String> getFileListInFolderByExtension(String path, final String extension) {
        //TODO - Create validation if file exist / trycatch
        File directoryPath = new File(path);
        final List<String> fileLst = new ArrayList<String>();

        FilenameFilter textFilefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                //String lowercaseName = name.toLowerCase();
                String lowercaseName = dir.getAbsolutePath().toLowerCase().concat("\\" + name.toLowerCase());
                if (lowercaseName.endsWith(extension)) {
                    fileLst.add(lowercaseName);
                    return true;
                } else {
                    return false;
                }
            }
        };
        @SuppressWarnings("unused")
        String filesList[] = directoryPath.list(textFilefilter);
        return fileLst;
    }

    /**
     * Function that return a extension from a file
     *
     * @param file
     * @return
     */
    public String getFileExtension(File file) {

        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}
