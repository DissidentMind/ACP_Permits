package model.db.data.processor;

import gui.controller.apache.poi.POIDataset;
import model.db.data.dictionary.Repo_DDAgents;
import model.db.utils.Db_Utility;
import org.apache.poi.ss.usermodel.*;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class TestStructure_Agents {

    private Connection currentConnection;
    private Repo_DDAgents repoAgent;

    public TestStructure_Agents() throws ClassNotFoundException, SQLException {
        String verifyQry = "SELECT COUNT(*) FROM [TGNH_Permits].[rol].[AGENTS]";
        String insertQry = "INSERT INTO [rol].[AGENTS](AGENT,EMAIL_AGENT,DEPTO_AGENT,ROL_AGENT) VALUES (";
        String pathFile =  "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-Agents.xlsx";

        int currentTab = 0;
        Boolean validTable = false;
        String table = "[rol].[AGENTS]";

        VaultValuesLoader.setDefaultDBName("TGNH_Permits");
        repoAgent = new Repo_DDAgents();

        //TODO if table doesn't exist

        if(Db_Utility.TestTableExist_JDBC("AGENTS")){
            currentConnection = Db_Utility.startConnection_WAuth(VaultValuesLoader.getDefaultDBName());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Statement stmt = currentConnection.createStatement();
            ResultSet rs = stmt.executeQuery(verifyQry);

            try{
                Workbook workbook = WorkbookFactory.create(new File(pathFile));
                Sheet firstSheet = workbook.getSheetAt(currentTab);
                Iterator<Row> iterator = firstSheet.iterator();

                int noOfColumns = firstSheet.getRow(0).getLastCellNum();
                //workbook.getNumberOfSheets()
                //System.out.println("Columns Found: "+noOfColumns);

                while (iterator.hasNext()){
                    Row nextRow = iterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        //System.out.println("Row: "+cell.getRowIndex());

                        if(cell.getRowIndex()>= 1){

                            if(cell.getColumnIndex()==0 && cell.getRowIndex()==1){
                                if(cell.getCellType() != CellType.BLANK){
                                    repoAgent.setValidRecord(true);
                                }
                            }

                            if(repoAgent.isValidRecord() && cell.getColumnIndex() <= noOfColumns){
                                switch(cell.getColumnIndex()){
                                    case 0:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                repoAgent.setAgentName(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                repoAgent.setAgentName(POIDataset.setUpPOIDataType_Identifier(null));
                                            }
                                        break;

                                    case 1:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                            repoAgent.setEmailAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setEmailAgent(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;

                                    case 2:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                            repoAgent.setDepotAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setDepotAgent(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;

                                    case 3:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                            repoAgent.setRolAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setRolAgent(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;
                                }
                            }
                        }
                    }

                    if(repoAgent.isValidRecord()==true){
                        String query = insertQry+"'"+repoAgent.getAgentName()+"','"+repoAgent.getEmailAgent()+"','"+repoAgent.getDepotAgent()+"','"+repoAgent.getRolAgent()+"')";
                        System.out.println("Query: "+query);
                        Db_Utility.executeInsertWithKeys(currentConnection, query);
                    }

                    rs.close();
                    stmt.closeOnCompletion();
                }

            }catch (FileNotFoundException e){
                e.printStackTrace();
                currentConnection.close();
            } catch (IOException e) {
                e.printStackTrace();
                currentConnection.close();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Table "+table+" is not present in current database. Verify.", "Table not found", JOptionPane.ERROR_MESSAGE);
        }


    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestStructure_Agents agentsProcess = new TestStructure_Agents();
    }

}
