package model.db.data.processor;

import gui.controller.apache.poi.POIDataset;
import model.db.data.tables.Repo_Censo;
import model.db.utils.Db_Utility;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class TestStructureRepo {

    public TestStructureRepo(Connection con, String pathFile, String verifyQuery, String insertQuery) throws SQLException {
        Repo_Censo p = new Repo_Censo();
        Boolean validTable = false;
        Boolean validRow = false;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(verifyQuery);

            if(rs.next()){
                System.out.println("Resultado Query: "+rs.getInt(1));
                if(rs.getInt(1) >= 0){
                    validTable = true;
                }
            }

            rs.close();
            stmt.closeOnCompletion();

            if(validTable){
                System.out.println("Update Censo Report - POWER BI");
                try {
                    Workbook workbook = WorkbookFactory.create(new File(pathFile));
                    Sheet firstSheet = workbook.getSheetAt(1);
                    Iterator<Row> iterator = firstSheet.iterator();

                    while (iterator.hasNext()){
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            /* Avoid Header Table */
                            if(cell.getRowIndex()>= 1){
                                if(cell.getColumnIndex()==0 && cell.getCellType() != CellType.BLANK){
                                    validRow = true;
                                    p.setIdMatriz(POIDataset.setUpPOIDataType_Identifier(cell));
                                    p.setFlagT(true);
                                }

                                if(p.getFlagT() && cell.getColumnIndex() <= 8){
                                    switch(cell.getColumnIndex()){

                                        case 1:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setIdCruce(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setIdCruce(null);
                                            }
                                            break;

                                        case 2:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setDescripcionPermiso(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setIdCruce(null);
                                            }
                                            break;

                                        case 3:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setDependencia(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setDependencia(null);
                                            }
                                            break;

                                        case 4:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setTramite(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setTramite(null);
                                            }
                                            break;

                                        case 5:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setFolioPermiso(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setFolioPermiso(null);
                                            }
                                            break;

                                        case 6:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setTipoPermiso(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setTipoPermiso(null);
                                            }
                                            break;

                                        case 7:
                                            if(POIDataset.setUpPOIDataType_Identifier(cell)!= null){
                                                p.setDetalle(POIDataset.setUpPOIDataType_Identifier(cell));
                                            }else{
                                                p.setDetalle(null);
                                            }
                                            break;
                                    }
                                }
                                //System.out.println("V: "+DatasetPOI.setUpPOIDataType_Identifier(cell));
                            }
                        }//End While Son
                        if(p.getIdCruce()!= null){
                            String query = insertQuery+"'"+p.getIdMatriz()+"','"+p.getIdCruce()+"','"+p.getDescripcionPermiso()+"','"+p.getDependencia()+"','"+p.getTramite()+"','"+p.getFolioPermiso()+"','"+p.getTipoPermiso()+"','"+p.getDetalle()+"')";
                            System.out.println(">: "+query);
                            Db_Utility.executeInsertWithKeys(con, query);
                        }else{
                            String query = insertQuery+"'"+p.getIdMatriz()+"',"+p.getIdCruce()+",'"+p.getDescripcionPermiso()+"','"+p.getDependencia()+"','"+p.getTramite()+"','"+p.getFolioPermiso()+"','"+p.getTipoPermiso()+"','"+p.getDetalle()+"')";
                            System.out.println(">: "+query);
                            Db_Utility.executeInsertWithKeys(con, query);
                        }
                        con.close();
                    }//End While Parent
                }catch(Exception e){
                    e.printStackTrace();
                    con.close();
                }
            }
        }catch(Exception e){
            con.close();
            System.out.println("Error Connection: "+e);
            System.exit(0);
        }
    }
}
