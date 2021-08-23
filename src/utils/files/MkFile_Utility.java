package utils.files;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MkFile_Utility {

    /**
     * Copy Entire Contents of File as String
     *
     * @param pathOutputFile
     * @param strToBeSaved
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
     * @param pathOutputFile
     * @param sstrToBeSaved
     */
    public void writeToFileApend(String pathOutputFile, String sstrToBeSaved) {
        try {
            Files.write(Paths.get(pathOutputFile), sstrToBeSaved.getBytes(), StandardOpenOption.APPEND);
            //FileWriter fw = new FileWriter(pathOutputFile, true);
            //fw.write(sstrToBeSaved);
            //fw.close();
        } catch (IOException e) {
        }
    }

}
