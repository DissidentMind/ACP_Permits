package gui.controller.apache.poi;

import gui.controller.init.DefaultsLoader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import utils.custom.DataStructures_Utility;
import utils.regex.Regex_Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POIDataset {

    static int incidencesFound = 0;
    static int currentRow = 0;

    static List<String> listPermits;

    static {
        listPermits = new ArrayList<String>();
    }

    static boolean validRecordFound = false;
    static String currentFileName;

    public static String getCurrentFileName() {
        return currentFileName;
    }

    public static void setCurrentFileName(String getCurrentFileName) {
        POIDataset.currentFileName = getCurrentFileName;
    }

    public static Boolean getValidRecordFound(){
        return validRecordFound;
    }

    public static void setValidRecordFound(Boolean flagValid){
        validRecordFound = flagValid;
    }

    public static void clearList(){
        listPermits.clear();
    }

    public static List<String> getListPermits() {
        return listPermits;
    }

    public static void setItemListPermits(String str) {
        POIDataset.listPermits.add(str);
    }

    public static int getCurrentRow() {
        return currentRow;
    }

    public static void setCurrentRow(int currentRow) {
        POIDataset.currentRow = currentRow;
    }

    /**
     * Function that converts list in string sepparated by commas and integrated in Object Array
     * @param inputFields
     * @return Object[]
     *
     */
    public Object[] generateDataSetRow(List<String> inputFields){
        //return String.join(",", inputFields);
        return new Object[] {String.join(",", inputFields)};
    }

    /**
     * Retrieve the cell content by the data type
     * @param cell
     */
    public static void getCellContentFromDataTypeDescription(Cell cell){

        if(cell.getCellType() != CellType.BLANK){
            CellType type = cell.getCellType();
            if (type == CellType.STRING) {
                System.out.println("[" + cell.getRowIndex() + ", "+ cell.getColumnIndex() + "] = STRING; Value = "+ cell.getRichStringCellValue().toString());
            } else if (type == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date dateValue = cell.getDateCellValue();
                    System.out.println("[" + cell.getRowIndex() + ", "+ cell.getColumnIndex() +"] = DATE;"+"Value: "+dateValue);
                }else{
                    System.out.println("[" + cell.getRowIndex() + ", "+ cell.getColumnIndex() + "] = NUMERIC; Value = "+ cell.getNumericCellValue());
                }
            } else if (type == CellType.BOOLEAN) {
                System.out.println("[" + cell.getRowIndex() + ", "+ cell.getColumnIndex() + "] = BOOLEAN; Value = "+ cell.getBooleanCellValue());
            } else if (type == CellType.BLANK) {
                System.out.println("[" + cell.getRowIndex() + ", "+ cell.getColumnIndex() + "] = BLANK CELL");
            } else if (type == CellType.FORMULA) {
                if(cell.getCachedFormulaResultType() == CellType.NUMERIC){
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date dateValue = cell.getDateCellValue();
                        System.out.println("[" + cell.getRowIndex() + ", "+ cell.getColumnIndex() +"] = DATE;"+"Value: "+dateValue);
                    }
                }else if(cell.getCachedFormulaResultType() == CellType.STRING){
                    System.out.println("[" + cell.getRowIndex() + ", "
                            + cell.getColumnIndex() +"] = STRING;"+"Value: "+cell.getCellFormula()+" Result Value: "+cell.getRichStringCellValue().toString());
                }
            }
        }
    }

    /**
     * Retrieve the cell content by the data type
     * @param cell
     */
    public static String getCellContentFromDataType(Cell cell){
        String datatypeIdnt = "";

        if(cell.getCellType() != CellType.BLANK){
            CellType type = cell.getCellType();
            if (type == CellType.STRING) {
                datatypeIdnt = cell.getRichStringCellValue().toString();
            } else if (type == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date dateValue = cell.getDateCellValue();
                    datatypeIdnt = ""+dateValue;
                }else{
                    datatypeIdnt = ""+cell.getNumericCellValue();
                }
            } else if (type == CellType.BOOLEAN) {
                datatypeIdnt = ""+cell.getBooleanCellValue();
            } else if (type == CellType.FORMULA) {
                if(cell.getCachedFormulaResultType() == CellType.NUMERIC){
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date dateValue = cell.getDateCellValue();
                        datatypeIdnt = ""+dateValue;
                    }
                }else if(cell.getCachedFormulaResultType() == CellType.STRING){
                    datatypeIdnt = cell.getRichStringCellValue().toString();
                }
            }
        }
        return datatypeIdnt;
    }

    public static String setUpPOIDataType_Identifier(Cell currentCell){
        /*
         * Validation
         * 1. Verify if blank cell: setItemListPermits(null) return null if blank
         * 2. Verify if formula: setItemListPermits(resultFormula)
         */
        String tempStr = null;

        switch (currentCell.getCellType()) {
            case BOOLEAN:
                tempStr = ""+currentCell.getBooleanCellValue();
                break;

            case BLANK:
                tempStr = null;
                break;

            case FORMULA:
                if(currentCell.getCachedFormulaResultType() == CellType.NUMERIC){
                    if (DateUtil.isCellDateFormatted(currentCell)) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateValue = currentCell.getDateCellValue();
                        tempStr = df.format(dateValue);
                    }else{
                        tempStr = ""+currentCell.getNumericCellValue();
                    }
                }else{
                    tempStr = currentCell.getRichStringCellValue().toString();
                    tempStr = tempStr.replace("'", "");
                }
                break;

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(currentCell)) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateValue = currentCell.getDateCellValue();
                    tempStr = df.format(dateValue);
                }else{
                    tempStr = ""+currentCell.getNumericCellValue();
                }
                break;

            case STRING:
                /*TODO Pre-validation if data format is no YYYY-MM-DD aplies string conversion*/
                if(currentCell.getColumnIndex() == 1 || currentCell.getColumnIndex() == 2){
                    if(currentCell.getRichStringCellValue().toString().equals("NA")||currentCell.getRichStringCellValue().toString().equals("N/A")){
                        tempStr = null;
                    }else{
                        tempStr = Regex_Utility.fixEnglishDateFormatToSQLDateFormat(currentCell.getRichStringCellValue().toString());
                    }

                }else{
                    tempStr = currentCell.getRichStringCellValue().toString();
                    tempStr = tempStr.replace("\\", "/");
                    //tempStr = tempStr.replace("{", "(");
                    tempStr = tempStr.replace("'", "");
                    //tempStr = tempStr.replace("\n", "");
                }
                break;

            default:
                break;
        }
        return tempStr;
    }

    public static void processBitacoraFile_List(Cell cell){
        //int counter
        //Columna 1 - FileName
        if(cell.getColumnIndex()==0 && cell.getCellType() != CellType.BLANK){
            if(Regex_Utility.isRegexContainedIntoSingleString(POIDataset.setUpPOIDataType_Identifier(cell), DefaultsLoader.REGEX_DOC_NAMING)){
                POIDataset.setValidRecordFound(true);
                POIDataset.setCurrentRow(cell.getRowIndex());
                POIDataset.setItemListPermits(cell.getStringCellValue());
                //System.out.println("Doct: "+cell.getStringCellValue());
            }
        }

        if(POIDataset.getValidRecordFound()){
            if(cell.getRowIndex() == POIDataset.getCurrentRow()){
                if(cell.getColumnIndex() > 0 && cell.getColumnIndex() <= 8){

                    POIDataset.setItemListPermits(POIDataset.setUpPOIDataType_Identifier(cell));

//					if(cell.getColumnIndex() == 8){
//						POIDataset.setItemListPermits(null);
//					}else{
//						POIDataset.setItemListPermits(POIDataset.setUpPOIDataType_Identifier(cell));
//					}

                }
            }
        }
    }

    /**
     * Function that process records by row and current cell.
     * @param currentRow
     * @param currentCell
     */
    public static void processBitacoraFile(Row currentRow, Cell currentCell){
        String auxDate = null;
        if(currentCell.getColumnIndex() == 0){

            if(Regex_Utility.isRegexContainedIntoSingleString(DefaultsLoader.REGEX_DOC_NAMING, currentCell.getRichStringCellValue().toString())){
                setValidRecordFound(true);
                setItemListPermits(setUpPOIDataType_Identifier(currentCell));
                setCurrentFileName(currentCell.getRichStringCellValue().toString());
            }
        }else if(currentCell.getColumnIndex() == 1 && getValidRecordFound()){
            auxDate = setUpPOIDataType_Identifier(currentCell);
            if(auxDate != null){
                if(Regex_Utility.isRegexContainedIntoSingleString(DefaultsLoader.REGEX_DATE_OK, auxDate)) {
                    setItemListPermits(auxDate);
                }else {
                    if(auxDate.length() <= 3){
                        setItemListPermits(null);
                    }else{
                        setItemListPermits(auxDate);
                    }
                }
            }
//			setItemListPermits(auxDate);
//			setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 2 && getValidRecordFound()){
            auxDate = setUpPOIDataType_Identifier(currentCell);
            if(auxDate != null){
                if(Regex_Utility.isRegexContainedIntoSingleString(DefaultsLoader.REGEX_DATE_OK, auxDate)) {
                    setItemListPermits(auxDate);
                }else {
                    if(auxDate.length() <= 3){
                        setItemListPermits(null);
                    }else{
                        setItemListPermits(auxDate);
                    }
                }
            }
//			setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 3 && getValidRecordFound()){
            setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 4 && getValidRecordFound()){
            setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 5 && getValidRecordFound()){
            setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 6 && getValidRecordFound()){
            setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 7 && getValidRecordFound()){
            setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }else if(currentCell.getColumnIndex() == 8 && getValidRecordFound()){
            setItemListPermits(setUpPOIDataType_Identifier(currentCell));
        }

    }


    public static String generateQueryProcess_List(){
        String tst = "INSERT INTO BD_BITACORAS(FOLIO_INTERNO, FECHA_COM, FECHA_RECEPCION, FILE_TYPE, ASUNTO, AUTOR, RECIBIDO, COMENTARIOS, REFERENCIA) VALUES(";
        String tstNetId = "INSERT INTO BD_FILENET(FILENET, FOLIO_INTERNO)VALUES(";

        List<String> listRecords = POIDataset.getListPermits();
        //List<String> outputQuery = new ArrayList<String>();

        int currentSize = POIDataset.getListPermits().size();

        if(currentSize != 0 || listRecords.isEmpty()){
            if(currentSize == 9){
                for (int i = 1; i < currentSize; i++) {

                    System.out.println("> "+listRecords.get(i));

                    if(i == POIDataset.getListPermits().size() - 1){ //Last Item
                        if(listRecords.get(i) == "null"){
                            tst = tst.concat(""+null+")"); //Verify when its lost a column (8) column
                        }else{
                            tst = tst.concat("'"+listRecords.get(i)+"')");
                        }

                        //		outputQuery.add(tst);

                    }else{ //Non Last Item
                        if(i==1){
                            tst = tst.concat("'"+POIDataset.getCurrentFileName()+"',");
                        }else{
                            tst = tst.concat("'"+listRecords.get(i)+"',");
                        }
                    }
                }//for
            }
        }

        return tst;
    }

    public static List<String> postProcessorBitacoraFile(){

        List<String> listRecords = POIDataset.getListPermits();
        List<String> outputQuery = new ArrayList<String>();
        int sizeList = listRecords.size();
        int netIdFound = 0;

        String tst = "INSERT INTO BD_BITACORAS(FOLIO_INTERNO, FECHA_COM, FECHA_RECEPCION, FILE_TYPE, ASUNTO, AUTOR, RECIBIDO, COMENTARIOS, REFERENCIA) VALUES(";
        String tstNetId = "INSERT INTO BD_FILENET(FILENET, FOLIO_INTERNO)VALUES(";

        if(sizeList != 0 ){
            if(sizeList == 9){

                for (int i = 1; i < sizeList; i++) {

                    //Finding Common records
                    if(i == sizeList - 1){ //Last Item
                        if(listRecords.get(i) == "null"){
                            tst = tst.concat(""+null+")"); //Verify when its lost a column (8) column
                        }else{
                            tst = tst.concat("'"+listRecords.get(i)+"')");
                        }

                        outputQuery.add(tst);

                    }else{ //Non Last Item
                        if(i==1){
                            tst = tst.concat("'"+POIDataset.getCurrentFileName()+"',");
                        }else{
                            tst = tst.concat("'"+listRecords.get(i)+"',");
                        }
                    }

                    //Finding FileNet
                    if(i>3 && i<=sizeList - 1){
                        if(listRecords.get(i) != null){
                            if(Regex_Utility.isRegexContainedIntoSingleString(DefaultsLoader.REGEX_FILENET, listRecords.get(i))){
                                netIdFound = Integer.parseInt(DataStructures_Utility.replaceCharMatchesWithAnotherChar(Regex_Utility.findCurrentIncidenceInStringMatches1(listRecords.get(i), DefaultsLoader.REGEX_FILENET),",",""));
                                tstNetId = tstNetId.concat("'"+netIdFound).concat("','"+POIDataset.getCurrentFileName()+"')");

                                outputQuery.add(tstNetId);

                            }
                        }

                    }
                }

            }else{
                //System.out.println("Non 9 size - bitacora");

                listRecords.add(null);
                for (int i = 1; i < sizeList; i++) {
                    if(i == sizeList - 1){ //Last Item
                        if(listRecords.get(i) == "null"){
                            tst = tst.concat(""+null+")"); //Verify when its lost a column (8) column
                        }else{ //Non Last Item
                            if(i==1){
                                tst = tst.concat("'"+POIDataset.getCurrentFileName()+"',");
                            }else{
                                tst = tst.concat("'"+listRecords.get(i)+"',");
                            }
                        }
                        outputQuery.add(tst);

                    }else{ //Non Last Item
                        //if(i>=1 && i<=4){ //2 to 4 Bitacora to review if are date format
                        if(i==2){
                            if(listRecords.get(i) != null){
                                if(!Regex_Utility.isRegexContainedIntoSingleString("([0-9]{4})-([0-9]{2})-([0-9]{2})", listRecords.get(i))){
                                    tst = tst.concat(null+",");
                                }else{
                                    tst = tst.concat("'"+listRecords.get(i)+"',");
                                }
                            }else{
                                tst = tst.concat(null+",");
                            }
                        }

                        tst = tst.concat("'"+listRecords.get(i)+"',");
                    }

                    //Finding FileNet
                    if(i>3 && i<=sizeList - 1){
                        if(listRecords.get(i) != null){
                            if(Regex_Utility.isRegexContainedIntoSingleString(DefaultsLoader.REGEX_FILENET, listRecords.get(i))){
                                netIdFound = Integer.parseInt(DataStructures_Utility.replaceCharMatchesWithAnotherChar(Regex_Utility.findCurrentIncidenceInStringMatches1(listRecords.get(i), DefaultsLoader.REGEX_FILENET),",",""));
                                tstNetId = tstNetId.concat("'"+netIdFound).concat("','"+POIDataset.getCurrentFileName()+"')");
                                outputQuery.add(tstNetId);
                            }
                        }else{
                            tst = tst.concat(null+",");
                        }
                    }

                } //Endfor
            }

        }else{
            System.out.println("Empty List - Skiped");
        }

        return outputQuery;
    }

}