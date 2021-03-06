package model.db.data.processor;

import gui.controller.apache.poi.POIDataset;
import model.db.data.dictionary.Repo_DDAgents;
import org.apache.poi.ss.usermodel.*;
import utils.db.Db_Utility;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class TestStructure_Agents {

    public TestStructure_Agents() throws ClassNotFoundException, SQLException {
        Repo_DDAgents repoAgent = new Repo_DDAgents();
        VaultValuesLoader.setDefaultDBName("TGNH_TVDR_Permits");
        //VaultValuesLoader.setDefaultDBName("TGNH_Permits");
        System.out.println("DB_Name New: "+VaultValuesLoader.getDefaultDBName());

        String verifyQry = "SELECT COUNT(*) FROM ["+VaultValuesLoader.getDefaultDBName()+"].[rol].[AGENTS]";
        String insertQry = "INSERT INTO [rol].[AGENTS](AGENT,EMAIL_AGENT,DEPTO_AGENT,ROL_AGENT) VALUES (";
        //String pathFile =  "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-Agents.xlsx";
        String pathFile = "D:\\DumpFiles\\TVDR-Agents.xlsx";

        int currentTab = 0;
        int noOfColumns = 0;
        String table = "[rol].[AGENTS]";

        //TODO if table doesn't exist
        if(Db_Utility.TestTableExist_JDBC("AGENTS")){
            Connection currentConnection = Db_Utility.startConnection_WAuth(VaultValuesLoader.getDefaultDBName());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Statement stmt = currentConnection.createStatement();
            ResultSet rs = stmt.executeQuery(verifyQry);

            try{
                Workbook workbook = WorkbookFactory.create(new File(pathFile));
                Sheet firstSheet = workbook.getSheetAt(currentTab);
                Iterator<Row> iterator = firstSheet.iterator();

                //Get the first row and count each header to consider each one as phtsicalcell
                //considering that exist almost one column.
                noOfColumns = firstSheet.getRow(1).getPhysicalNumberOfCells();

                System.out.println("Columns Found: " + noOfColumns);

                if(noOfColumns != 0) {
                    while (iterator.hasNext()) {
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();

                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();

                            if (cell.getRowIndex() >= 1) {
                                if (cell.getColumnIndex() == 0 && cell.getRowIndex() == 1) {
                                    if (cell.getCellType() != CellType.BLANK) {
                                        repoAgent.setValidRecord(true);
                                        //noOfColumns = firstSheet.getPhysicalNumberOfCells();
                                    }
                                }

                                if (repoAgent.isValidRecord() && cell.getColumnIndex() <= noOfColumns) {
                                    switch (cell.getColumnIndex()) {
                                        case 0:
                                            repoAgent.setAgentName(POIDataset.setUpPOIDataType_Identifier(cell));
                                            break;
                                        case 1:
                                            repoAgent.setEmailAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                            break;
                                        case 2:
                                            repoAgent.setDepotAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                            break;
                                        case 3:
                                            repoAgent.setRolAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                            break;
                                    }
                                }
                            }
                        }

                        if (repoAgent.isValidRecord()) {
                            String query = insertQry + "'" + repoAgent.getAgentName() + "','" + repoAgent.getEmailAgent() + "','" + repoAgent.getDepotAgent() + "','" + repoAgent.getRolAgent() + "')";
                            System.out.println("Query: " + query);
                            Db_Utility.executeInsertWithKeys(currentConnection, query);
                        }

                        rs.close();
                        stmt.closeOnCompletion();
                    }
                }//IF of validation to firt column
            } catch (IOException e){
                e.printStackTrace();
                currentConnection.close();
            }
        }else{
            //System.out.println(">> "+Db_Utility.TestTableExist_JDBC("AGENTS"));
            JOptionPane.showMessageDialog(null, "Table "+table+" is not present in current database. Verify.", "Table not found", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestStructure_Agents agentsProcess = new TestStructure_Agents();
    }
}
