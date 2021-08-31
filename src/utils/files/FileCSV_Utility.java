package utils.files;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileCSV_Utility {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private static final String COMMA_SEPARATOR = ",";

    /**
     * Function that read the records line by line using readLine() in BufferedReader.
     * Then we'll split the line into tokens based on the comma delimiter.
     *
     * @param {String} csvFile
     * @return records
     */
    public List<List<String>> readLineByLineCSVintoList_BufferReader(String csvFile) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_SEPARATOR);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return records;
    }

    /**
     * We’re going to use a java.util.Scanner to run through the contents of the file and retrieve lines serially, one by one.
     *
     * @param {String} csvFile
     * @return records
     */
    public List<List<String>> readLibeByLineCSVintoList_Scanner(String csvFile) {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(csvFile));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return records;
    }

    /**
     * Function that parse the lines and store it into an array
     *
     * @param {String} line
     * @return
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_SEPARATOR);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    /**
     * This function retrieve from CSV File the specified column to move it to a single List<String>
     * We’re going to use a java.util.Scanner to run through the contents of the file and retrieve lines serially, one by one.
     *
     * @param {String} csvFilePathPath Path CSV File
     * @param {int}    columnInput Number of Column from the CSV File that will be move to the List
     * @return
     * @throws Exception
     */
    public List<String> getRowStringFromCSVtoList(String csvFilePathPath, int columnInput) throws Exception {
        List<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(new File(csvFilePathPath));

        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            list.add(line.get(columnInput));
            //System.out.println("> "+line);
        }

        scanner.close();
        return list;
    }

    /**
     * Function parse line into list
     *
     * @param {String} cvsLine
     * @return List<String>
     */
    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    /**
     * @param {String} cvsLine
     * @param {char}   separators
     * @return
     */
    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    /**
     * @param cvsLine
     * @param separators
     * @param customQuote
     * @return
     */
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

    public static void copyFileUsingStream(File source, File dest) throws IOException {
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
}
