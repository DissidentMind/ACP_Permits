package utils.regex;

import utils.files.CopyFiles_Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex_Utility {

    List<String> listInLetters;
    List<String> listOutLetters;

    public List<String> getListInLetters() {
        return listInLetters;
    }

    public void setListInLetters(String listInLetters) {
        this.listInLetters.add(listInLetters);
    }

    public List<String> getListOutLetters() {
        return listOutLetters;
    }

    public void setListOutLetters(String listOutLetters) {
        this.listInLetters.add(listOutLetters);
    }

    /**
     * Function search string in list. Validate the pattern and exclude the "Anexos" location if is found.
     * Execute the copy of found files.
     * <p>
     * Validate if the file name exist. Getting the file name from "FILE" Class from gotten filepath.
     *
     * @param strToSearch
     * @param listToFind
     * @param destinationPath
     * @return void
     */
    public void isStringContainedInListAndCopy(String strToSearch, List<String> listToFind, String destinationPath) {

        for (int i = 0; i < listToFind.size(); i++) {

            Pattern pattern = Pattern.compile(strToSearch);
            Matcher m = pattern.matcher(listToFind.get(i));

            Pattern patternAvoid = Pattern.compile("ANEXOS", Pattern.CASE_INSENSITIVE);
            Matcher matcherAvoid = patternAvoid.matcher(listToFind.get(i));

            while (m.find()) {

                //Validate if file already exist

                if (!new File(destinationPath.concat(new File(listToFind.get(i)).getName())).exists()) {

                    if (!matcherAvoid.find()) {

                        CopyFiles_Utility getCopyFile = new CopyFiles_Utility();
                        getCopyFile.generateCopyFileInPath(listToFind.get(i), destinationPath);

                        System.out.println("File Copied: " + listToFind.get(i));
                    }
                }
            }
        }
    }

    /**
     * Get String to Search and Search coincidences in listToFind
     *
     * @param strToSearch
     * @param listToFind
     * @return String
     */
    public String isStringContainedInList(String strToSearch, List<String> listToFind) {

        Pattern pattern = Pattern.compile(strToSearch);
        String foundFilePath = "";

        for (String value : listToFind) {
            Matcher m = pattern.matcher(value);

            if (m.find()) {
                foundFilePath = value;
            }
        }
        return foundFilePath;
    }

    /**
     * Get String to Search and Search coincidences in listToFind
     *
     * @param strToSearch
     * @param listToFind
     * @return boolean
     */
    public Boolean isRegexContainedInList(String strToSearch, List<String> listToFind) {

        Pattern pattern = Pattern.compile(strToSearch);
        Boolean flagConfirm = false;

        for (String value : listToFind) {
            Matcher m = pattern.matcher(value);

            if (m.find()) {
                //System.out.println("String Found: "+value);
                flagConfirm = true;
            }
        }
        return flagConfirm;
    }

    /**
     * @param strToSearch
     * @param toFind
     * @return Boolean
     */
    public Boolean isRegexContainedIntoSingleString(String strToSearch, String toFind) {

        Pattern pattern = Pattern.compile(strToSearch);
        Matcher m = pattern.matcher(toFind);
        Boolean flagExist = false;

        while (m.find()) {
            //System.out.println("Pattern found from " + m.start() + " to " + (m.end()-1));
            flagExist = true;
        }
        return flagExist;
    }

    /**
     * @param strToSearch
     * @param toFindArray
     */
    public void isRegexContainedIntoExhaustiveSearch_CSV(String strToSearch, CharSequence toFindArray) {

        for (int i = 0; i < toFindArray.length(); i++) {

            //System.out.println("Searching in: "+toFindArray[i]);
            //isRegexContainedIntoSingleString("TXTL-TGNH-CNA-0000-0165",toFindArray[i]);

            Pattern pattern = Pattern.compile(strToSearch);
            //Matcher m = pattern.matcher(toFindArray);
            Matcher m = pattern.matcher(toFindArray);
            while (m.find()) {
                System.out.println("Pattern found from " + m.start() + " to " + (m.end() - 1));
            } // End While
        } //End For

    }

    public boolean findCurrentIncidenceInStringMatches(String textToReview, String regexCondition) {
        System.out.println(textToReview);
        return textToReview.matches(regexCondition);
    }

    public String findCurrentIncidenceInStringMatches1(String textToReview, String regexCondition) {
        String rtnStr = null;
        Pattern pattern = Pattern.compile(regexCondition); //,Pattern.CASE_INSENSITIVE
        Matcher matcher = pattern.matcher(textToReview);

        if (matcher.find()) {
            System.out.println(matcher.group(0) + " => " + matcher.group());

        }
        return rtnStr;
    }


    /**
     * Function that returns a List of Strings with contains the input parameter
     *
     * @param inputList
     * @param inputSearchParam
     * @return
     */
    public static List<String> getListIfCoindicendeFound(List<String> inputList, String inputSearchParam) {
        List<String> tempList = new ArrayList<String>();

        Regex_Utility frU = new Regex_Utility();

        for (int i = 0; i < inputList.size(); i++) {
            if (frU.findCurrentIncidenteInString(inputList.get(i).toString(), inputSearchParam) != null) {

                File tmp = new File(inputList.get(i));
                tempList.add(tmp.getName());
            }
        }
        return tempList;
    }

    /**
     * Function that returns a nested key and filename into Hash Map if the string contains the input parameter
     *
     * @param inputList
     * @param inputSearchParam
     * @return
     */
    public static HashMap<Integer, String> getHashIfCoincidenceFound(List<String> inputList, String inputSearchParam) {

        HashMap<Integer, String> map = new HashMap<>();
        Regex_Utility frU = new Regex_Utility();

        for (int i = 0; i < inputList.size(); i++) {
            if (frU.findCurrentIncidenteInString(inputList.get(i).toString(), inputSearchParam) != null) {

                File tmp = new File(inputList.get(i));
                map.put(i, tmp.getName().toString());
            }
        }
        return map;
    }

    //------------ Working and Tested ----------------------------
    public String findCurrentIncidenteInString(String textToReview, String regexCondition) {
        String rtnStr = null;
        Pattern pattern = Pattern.compile(regexCondition); //,Pattern.CASE_INSENSITIVE
        Matcher matcher = pattern.matcher(textToReview);
        if (matcher.find()) {
            rtnStr = matcher.group(0);

            //this.setListOutLetters(matcher.group(2));
            //this.setListInLetters(matcher.group(3));
        }
        return rtnStr;
    }

    public String findCurrentIncidenteInString_Insensitive(String textToReview, String regexCondition) {
        String rtnStr = null;
        Pattern pattern = Pattern.compile(regexCondition, Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(textToReview);
        if (matcher.find()) {
            rtnStr = matcher.group(0);
        }
        return rtnStr;
    }

    public String findCurrentIncidencesSiglasInString_ByGroupNum(String regex, String str, int groupId) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);
        String rstString = "";

        while (matcher.find()) {
            //System.out.println("Full match: " + matcher.group(0));
            //for (int i = 1; i <= matcher.groupCount(); i++) {
            //  System.out.println("Group " + i + ": " + matcher.group(i));
            //}
            rstString = matcher.group(groupId);
        }
        return rstString;
    }

}
