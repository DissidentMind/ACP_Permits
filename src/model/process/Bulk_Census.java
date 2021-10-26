package model.process;

import gui.controller.apache.poi.POIDataset;
import model.db.data.tables.Repo_Censo;
import model.db.data.tables.Repo_Deadlines;
import utils.db.Db_Utility;
import org.apache.poi.ss.usermodel.*;
import vault.VaultValuesLoader;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bulk_Census {
    private boolean validTable = false;
    private final List<Repo_Deadlines> data = new ArrayList<>();
    private final static String validaQuery = "SELECT COUNT(*) FROM [TGNH_TVDR_Permits].[dbo].[CENSO_PERMISOS]";
    private final static String insertQuery = "INSERT INTO CENSO_PERMISOS(ID_MATRIZ,ID_CRUCE,DESCRIPCION,DEPENDENCIA,TRAMITE,PERMISO,DEPARTAMENTO,DETALLE) VALUES (";
    private final Repo_Censo p = new Repo_Censo();

    /*Bulk_Censo(){
        super();
        p = new Repo_Censo();
        Connection con = Db_Utility.startConnection_WAuth(VaultValuesLoader.getDefaultDBName());
        processCenso_Bulk(con);
    }*/

    public void processCenso_Bulk(Connection con){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(validaQuery);

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
                    Workbook workbook = WorkbookFactory.create(new File(VaultValuesLoader.getDefaultDowPathFol()));
                    Sheet firstSheet = workbook.getSheetAt(1);
                    Iterator<Row> iterator = firstSheet.iterator();
                    while (iterator.hasNext()){
                        Row nextRow = iterator.next();
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            if(cell.getRowIndex()>= 1){

                                if(cell.getColumnIndex()==0 && cell.getCellType() != CellType.BLANK){
                                    boolean validRow = true;
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
                            }
                        }//End While Son

                        String query;
                        if(p.getIdCruce() != null){
                            query = insertQuery + "'" + p.getIdMatriz() + "','" + p.getIdCruce() + "','" + p.getDescripcionPermiso() + "','" + p.getDependencia() + "','" + p.getTramite() + "','" + p.getFolioPermiso() + "','" + p.getTipoPermiso() + "','" + p.getDetalle() + "')";
                        }else{
                            query = insertQuery + "'" + p.getIdMatriz() + "'," + p.getIdCruce() + ",'" + p.getDescripcionPermiso() + "','" + p.getDependencia() + "','" + p.getTramite() + "','" + p.getFolioPermiso() + "','" + p.getTipoPermiso() + "','" + p.getDetalle() + "')";
                        }
                        System.out.println(">: "+query);
                        Db_Utility.executeInsertWithKeys(con, query);
                    }//End While Parent

                }catch(Exception e){
                    e.printStackTrace();
                    //System.out.println("Error in Connection: "+e);
                }
            }
        }catch(Exception e){
            System.out.println("Error Connection: "+e);
            System.exit(0);
        }
    }
}
