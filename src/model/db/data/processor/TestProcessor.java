package model.db.data.processor;

import model.db.utils.Db_Utility;
import vault.VaultValuesLoader;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TestProcessor {

    private Boolean validTable;

    public TestProcessor(){
        super();
    }

    public static void main(String[] args) throws SQLException {

        String  verifyQry = "SELECT COUNT(*) FROM [TGNH_TVDR_Permits].[dbo].[CENSO_PERMISOS]";
        String insertQry = "INSERT INTO CENSO_PERMISOS(ID_MATRIZ,ID_CRUCE,DESCRIPCION,DEPENDENCIA,TRAMITE,PERMISO,DEPARTAMENTO,DETALLE) VALUES (";


        Db_Utility dbUtil = new Db_Utility();
        Connection con = dbUtil.startConnection_WAuth(VaultValuesLoader.getDefaultDBName());

        TestStructureRepo tsR = new TestStructureRepo(con,VaultValuesLoader.getDefaultLog_TVDR(),verifyQry,insertQry);



    }

}
