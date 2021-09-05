package utils.files;

import java.awt.*;
import java.io.File;

public class FileValidations_Utility {
    /**
     * Validations for system requirements
     */

    /**
     * Function that confirms if file exist
     *
     * @param filePathString
     * @return
     */
    public static Boolean fileExistInPath(String filePathString) {
        boolean flagConfirm = false;
        File f = new File(filePathString);

        if (f.exists() && !f.isDirectory()) {
            flagConfirm = true;
        }
        return flagConfirm;
    }

    /**
     * Function that confirms if path is a folder
     *
     * @param filePathString
     * @return
     */
    public static Boolean fileIsFolder(String filePathString) {
        boolean flagConfirm = false;
        File f = new File(filePathString);
        if (f.exists() && f.isDirectory()) {
            flagConfirm = true;
        }
        return flagConfirm;
    }

    /**
     * Function that open a file if this exist in path
     *
     * @param file
     */
    public void openPDFFileIfExistInPath(String file) {
        try {
            File pdfFile = new File(file);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Awt Desktop is not supported!");
                }
            } else {
                System.out.println("File is not exists!");
            }
            System.out.println("Done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
