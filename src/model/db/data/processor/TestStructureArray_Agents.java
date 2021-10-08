package model.db.data.processor;

import gui.controller.apache.poi.POIDataset;
import model.db.data.dictionary.Repo_DDAgents;
import model.db.utils.Db_Utility;
import org.apache.poi.ss.usermodel.*;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class TestStructureArray_Agents {

    private String [][] itemsArray;
    private int rowSize;
    private int colSize;

    public int getRowSize() {
        return rowSize;
    }
    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }
    public int getColSize() {
        return colSize;
    }
    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public String[][] getItemsArray() {
        return itemsArray;
    }

    public void initializeArray(int intCol,int intRow){
        this.itemsArray = new String[intCol][intRow];
    }

    public void addItemsToArray(String valueInput){
            for (int r=0; r<itemsArray.length; r++) {
                for (int c=0; c<itemsArray[r].length; c++) {
                    if (itemsArray[r][c] == null) {
                        itemsArray[r][c] = valueInput;
                    }
                }
            }

/*            for (int i = 0; i < this.getRowSize(); i++)
                for (int j = 0; j < this.getColSize(); j++)
                    if (itemsArray[i][j] == null) {
                        itemsArray[i][j] = valueInput;
                    }*/
    }

    public void replacerArrayAsNull(String array[][]){
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColSize(); j++) {
                if(itemsArray[i][j].equals("null")){
                    itemsArray[i][j] = null;
                }
            }
        }
    }

    public void displayArray(String[][] itemsArray){
        for (int i = 0; i < itemsArray.length; i++) {
            for (int j = 0; j < itemsArray[i].length; j++) {
                System.out.println(i+" - "+j+" > "+itemsArray[i][j]);
            }
        }
    }
    public TestStructureArray_Agents(String verifyQry, String insertQry, String pathFile, int currentTab, int totalCols, int totalRows, String dbName, String tableName)throws ClassNotFoundException, SQLException {
        Repo_DDAgents repoAgent = new Repo_DDAgents();
        initializeArray(totalCols,totalRows);

        if(Db_Utility.TestTableExist_JDBC(tableName)){
            Connection currentConnection = Db_Utility.startConnection_WAuth(dbName);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Statement stmt = currentConnection.createStatement();
            ResultSet rs = stmt.executeQuery(verifyQry);
            try{
                Workbook workbook = WorkbookFactory.create(new File(pathFile));
                Sheet firstSheet = workbook.getSheetAt(currentTab);
                Iterator<Row> iterator = firstSheet.iterator();
                totalCols = firstSheet.getRow(1).getPhysicalNumberOfCells();

                if(totalCols != 0) {
                    while (iterator.hasNext()) {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            if (cell.getRowIndex() >= 1) {
                                if (cell.getColumnIndex() == 0 && cell.getRowIndex() == 1) {
                                    if (cell.getCellType() != CellType.BLANK) {
                                        repoAgent.setValidRecord(true);
                                    }
                                }
                                if (repoAgent.isValidRecord() && cell.getColumnIndex() <= totalCols) {
                                    for (int i = 0; i <= totalCols; i++) {
                                        if(cell.getColumnIndex() == i){
                                            System.out.println("> "+POIDataset.setUpPOIDataType_Identifier(cell));
                                            //addItemsToArray(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }
                                    }
                                }
                            }
                        }

                       /* if (repoAgent.isValidRecord()) {
                            String query = insertQry + "'" + repoAgent.getAgentName() + "','" + repoAgent.getEmailAgent() + "','" + repoAgent.getDepotAgent() + "','" + repoAgent.getRolAgent() + "')";
                            System.out.println("Query: " + query);
                            Db_Utility.executeInsertWithKeys(currentConnection, query);
                        }*/

                        displayArray(this.getItemsArray());
                    }
                    rs.close();
                    stmt.closeOnCompletion();

                }//IF of validation to firt column
            } catch (IOException e){
                e.printStackTrace();
                currentConnection.close();
            }
        }else{
            //System.out.println(">> "+Db_Utility.TestTableExist_JDBC("AGENTS"));
            JOptionPane.showMessageDialog(null, "Table "+tableName+" is not present in current database. Verify.", "Table not found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        VaultValuesLoader.setDefaultDBName("TGNH_TVDR_Permits");

        String verifyQry = "SELECT COUNT(*) FROM ["+ VaultValuesLoader.getDefaultDBName()+"].[rol].[AGENTS]";
        String insertQry = "INSERT INTO [rol].[AGENTS](AGENT,EMAIL_AGENT,DEPTO_AGENT,ROL_AGENT) VALUES (";
        String pathFile = "D:\\DumpFiles\\TVDR-Agents.xlsx";
        int currentTab = 0;
        int noOfColumns = 4;
        int totalRows = 4;
        String dbName = "TGNH_TVDR_Permits";
        String tableName = "Agents";

        TestStructureArray_Agents tstArray = new TestStructureArray_Agents(
                verifyQry,insertQry,pathFile,currentTab,noOfColumns,totalRows,dbName,tableName);
    }
}
