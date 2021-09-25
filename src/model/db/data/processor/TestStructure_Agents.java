package model.db.data.processor;

import gui.controller.apache.poi.POIDataset;
import model.db.data.dictionary.Repo_DDAgents;
import model.db.utils.Db_Utility;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.Iterator;

public class TestStructure_Agents {

    private Connection con;
    private Db_Utility dbUtil;
    private Repo_DDAgents repoAgent;

    public TestStructure_Agents() throws ClassNotFoundException, SQLException {
        String verifyQry = "SELECT COUNT(*) FROM [TGNH_PERMITS].[rol].[AGENTS]";
        String insertQry = "INSERT INTO [rol].[AGENTS](AGENT,EMAIL_AGENT,DEPTO_AGENT,ROL_AGENT) VALUES (";
        String pathFile =  "C:\\Users\\fabio_rodriguez\\OneDrive - TransCanada Corporation\\Documents\\IT\\Paths-CSV-CopyFiles\\TVDR-Agents.xlsx";

        Boolean validTable = false;
        String table = "[rol].[AGENTS]";

        con = dbUtil.startConnection_WAuth(VaultValuesLoader.getDefaultDBName());
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(verifyQry);

        //if(dbUtil.TestTableExist_JDBC("[rol].[AGENTS]")){
        if(dbUtil.TestTableExist_JDBC("AGENTS")){

            try{
                Workbook workbook = WorkbookFactory.create(new File(pathFile));
                Sheet firstSheet = workbook.getSheetAt(1);
                Iterator<Row> iterator = firstSheet.iterator();

                int noOfColumns = firstSheet.getRow(0).getLastCellNum();

                System.out.println("Columns Found: "+noOfColumns);

                while (iterator.hasNext()){

                    Row nextRow = iterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        if(cell.getRowIndex()>= 1){
                            if(cell.getColumnIndex()==0 && cell.getCellType() != CellType.BLANK){
                                repoAgent.setAgentName(POIDataset.setUpPOIDataType_Identifier(cell));
                                repoAgent.setValidRecord(true);
                            }

                            if(repoAgent.isValidRecord() && cell.getColumnIndex() <= noOfColumns){
                                switch(cell.getColumnIndex()){

                                    case 1:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                           repoAgent.setAgentName(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setAgentName(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;

                                    case 2:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                            repoAgent.setEmailAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setEmailAgent(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;

                                    case 3:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                            repoAgent.setDepotAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setDepotAgent(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;

                                    case 4:
                                        if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                            repoAgent.setRolAgent(POIDataset.setUpPOIDataType_Identifier(cell));
                                        }else{
                                            repoAgent.setRolAgent(POIDataset.setUpPOIDataType_Identifier(null));
                                        }
                                        break;

                                }

                                if(repoAgent.isValidRecord()){
                                    String query = insertQry+"'"+repoAgent.getAgentName()+"',"+repoAgent.getEmailAgent()+",'"+repoAgent.getDepotAgent()+"','"+repoAgent.getRolAgent()+"')";
                                    Db_Utility.executeInsertWithKeys(con, query);
                                }



                            }

                        }

                    }

                }

            }catch (Exception e){
                e.printStackTrace();
                con.close();
            }

        }else{
            JOptionPane.showMessageDialog(null, "Table "+table+" is not present in current database. Verify.", "Table not found", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
        stmt.closeOnCompletion();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestStructure_Agents agentsProcess = new TestStructure_Agents();
    }

}
