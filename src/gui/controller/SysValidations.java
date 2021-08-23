package gui.controller;

import java.awt.*;
import java.io.File;

public class SysValidations {
    /**
     * Validations for system requirements
     */

    /**
     * Function that confirms if file exist
     *
     * @param file
     * @return
     */
    public Boolean fileExistInPath(String file) {
        Boolean flagExist = false;
        try {
            File pdfFile = new File(file);
            if (pdfFile.exists()) {
                flagExist = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flagExist;
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
