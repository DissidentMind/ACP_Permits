package gui.utils;

import gui.controller.init.SettingsStat;

import javax.swing.*;
import java.io.File;

public class FileChooser_Utility extends JPanel {

    private static final String newline = "\n";

    public static String FileChooserGetPath(int selectFileChooser) {
        String fileChooserPath = "";
        JFileChooser fc = new JFileChooser();
        switch (selectFileChooser) {
            case 0: //Open Files
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                //returnVal = fc.showOpenDialog(FileChooser_Utility.this);
                int returnVal = fc.showOpenDialog(SettingsStat.getCurrentParentPanel());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println("Opening: " + file.getName() + "." + newline);
                    System.out.println("File Path: " + file.getAbsolutePath());

                    fileChooserPath = file.getAbsolutePath();

                } else {
                    System.out.println("Open command cancelled by user." + newline);
                }
                break;
            case 1: //Open Folder - Set Destination Folder
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //returnVal = fc.showOpenDialog(FileChooser_Utility.this);

                returnVal = fc.showOpenDialog(SettingsStat.getCurrentParentPanel());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println("Opening: " + file.getName() + "." + newline);
                    System.out.println("File Path: " + file.getAbsolutePath());

                    fileChooserPath = file.getAbsolutePath();

                } else {
                    System.out.println("Open command cancelled by user." + newline);
                }
                break;
            case 2: //Save File
                //returnVal = fc.showSaveDialog(FileChooser_Utility.this);

                returnVal = fc.showOpenDialog(SettingsStat.getCurrentParentPanel());

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would save the file.
                    System.out.println("Saving: " + file.getName() + "." + newline);

                    fileChooserPath = file.getAbsolutePath();

                } else {
                    System.out.println("Save command cancelled by user." + newline);
                }
                break;
        }
        return fileChooserPath;
    }
}
