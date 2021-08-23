package utils.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class MkDirFolder_Utility {
    /**
     * @param origin
     * @param destDir
     * @param destination
     * @throws IOException
     */
    public static void copyFile_Directory(String origin, String destDir, String destination) throws IOException {

        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        File directory = new File(String.valueOf(destDir));

        if (!directory.exists()) {
            directory.mkdir();
        }
        //overwrite the destination file if it exists, and copy
        // the file attributes, including the rwx permissions
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES

        };
        Files.copy(FROM, TO, options);
    }

    /**
     * @param destDir
     * @throws IOException
     */
    public static void createDirectory(String destDir) throws IOException {
        File directory = new File(String.valueOf(destDir));

        if (!directory.exists()) {
            directory.mkdir();
        }

    }

    /**
     * @param destDir
     * @param pathMain
     * @return String
     * @throws IOException
     */
    public String createDirectoryAndGetPath(String destDir, String pathMain) throws IOException {
        File directory = new File(pathMain.concat(String.valueOf(destDir)));

        if (!directory.exists()) {
            directory.mkdir();
        }
        return pathMain.concat(directory.getName());
    }
}

