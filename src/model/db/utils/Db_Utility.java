package model.db.utils;

import gui.controller.init.ConnectionStat;
import vault.VaultValuesLoader;

import javax.swing.*;
import java.sql.*;

//Arguments
//-Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\;${env_var:PATH}"
//-Djava.library.path="C:\Users\evanf\Documents\Projects[Source]\JavaLibs\sqljdbc_8.2\enu\auth\x64"
//-Djava.library.path="C:\Users\evanf\Documents\Projects[Source]\JavaLibs\sqljdbc_9.2\enu\auth\x64"

public class Db_Utility {
    /**
     * Function that test connection string for sql server using jdbc Ver 6.4
     *
     * @param server //localhost\\sqlexpress
     * @param port   //1433
     * @param user   //TCPL\fabio_rodriguez
     * @param pass
     * @param db     //TGNH_Permits
     */
    public void TestConnection_JDBC(String server, int port, String user, String pass, String db) {
        try {
            Class.forName(VaultValuesLoader.JDBC_SQLSERVER_DRIVER);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://"+ server +":" + port + ";user=" + user + ";password=" + pass + ";databaseName=" + db + "";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            System.out.println("# - Statement Created");
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM CAT_MUNICIPIO");
            System.out.println("# - Statement Created");
                if (rs.next()) {
                    System.out.println("Resultado Query: " + rs.getInt(1));
                }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Closed Connection");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void TestConnection_JDBC(String user, String pass, String db) {
        try {
            Class.forName(VaultValuesLoader.JDBC_SQLSERVER_DRIVER);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://"+ VaultValuesLoader.getDefaultHost() +":" + VaultValuesLoader.getJdbcPort() + ";user=" + user + ";password=" + pass + ";databaseName=" + db + "";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            System.out.println("# - Statement Created");
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM CAT_MUNICIPIO");
            System.out.println("# - Statement Created");
            if (rs.next()) {
                System.out.println("Resultado Query: " + rs.getInt(1));
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Closed Connection");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void TestConnection_JDBC_WindowsAuthentification(String queryValidation) {
        /*
        SELECT OBJECT_ID FROM sys.objects WHERE name = '';
         */
        try {
            Class.forName(VaultValuesLoader.JDBC_SQLSERVER_DRIVER);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://"+ VaultValuesLoader.getDefaultHost() +":" + VaultValuesLoader.getDefaultHost() + ";databaseName=" + VaultValuesLoader.getDefaultDBName() + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            System.out.println("# - Statement Created");
            ResultSet rs = stmt.executeQuery(queryValidation);
            System.out.println("# - Statement Created");
                if (rs.next()) {
                    System.out.println("Resultado Query: " + rs.getInt(1));
                }
                rs.close();
                stmt.close();
                con.close();
            System.out.println("Closed Connection");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Function that validate if connection exist - Driver, Connection, Statement and if default table exist
     * @param server
     * @param port
     * @param db
     * @param table
     */
    public static void TestConnection_JDBC(String server, int port, String db, String table) {
        ConnectionStat connectStatus = new ConnectionStat();
        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
                System.out.println("Driver Loaded...");
            String jdbcUrl = "jdbc:sqlserver://" + server +":" + port + ";databaseName=" + db + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
                System.out.println("# - Connection Obtanied...");
            connectStatus.setValidateExistConnection(true);
            connectStatus.setValidateExistDb(true);
            Statement stmt = con.createStatement();
                System.out.println("# - Statement Created...");
                System.out.println("# - Verify Table Permits...");
            ResultSet rs = stmt.executeQuery("SELECT OBJECT_ID FROM sys.objects WHERE name = '" + table + "';");
            if (rs == null) {
                JOptionPane.showMessageDialog(null, "Database connection is available but table was not found. Verify.", "Table Doesn't Exist", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }else{
                System.out.println("# - Table was Found... Starting GUI");
                connectStatus.setValidateExistTable(true);
            }
            rs.close();
            //**********************************************
             /* String SQL = "SELECT * FROM "+table+";";
                ResultSet rSet = stmt.executeQuery(SQL);
                displayRow("PRODUCTS", rSet);
                rSet.close();*/
            //**********************************************
            stmt.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The connection to Database is not available. Verify.", "DB Verification Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error: " + e);
            System.exit(0);
        }
    }

    public static boolean TestTableExist_JDBC(String table) {
            ConnectionStat connectStatus = new ConnectionStat();
            boolean tableExist = false;
        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
            String jdbcUrl = "jdbc:sqlserver://" + VaultValuesLoader.getDefaultHost() +":" + VaultValuesLoader.getJdbcPort() + ";databaseName=" + VaultValuesLoader.getDefaultDBName()+ ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            connectStatus.setValidateExistConnection(true);
            connectStatus.setValidateExistDb(true);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT OBJECT_ID FROM sys.objects WHERE name = '" + table + "';");
            if (rs != null) { tableExist = true; }
                rs.close();
                stmt.close();
                con.close();
        } catch (Exception e) {
            System.out.println("Exception: "+e);
            JOptionPane.showMessageDialog(null, "Query Fails. Verify.", "Error - Query Fails", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return tableExist;
    }

    private static void displayRow(String title, ResultSet rs) throws SQLException {
        System.out.println(title);
        while (rs.next()) {
            System.out.println(rs.getString("ID_MATRIZ") + " : " + rs.getString("ID_PERMISO"));
        }
    }

    public static Connection startConnection_WAuth(String db) {
        Connection con = null;
        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://" + VaultValuesLoader.getDefaultHost() + ":" + VaultValuesLoader.getJdbcPort() + ";databaseName=" + db + ";integratedSecurity=true";
            con = DriverManager.getConnection(jdbcUrl);
            //Statement stmt = con.createStatement();
            //stmt.executeQuery("SET NOCOUNT ON");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return con;
    }

    /**
     * Function that store in database - used in program.
     * @param con
     * @param query
     */
    public static void executeInsertWithKeys(Connection con, String query) {
        try {
            Statement stmt = con.createStatement();
            int count = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("Generated Keys: "+count);
            ResultSet rs = stmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                do {
                    for (int i = 1; i <= columnCount; i++) {
                        String key = rs.getString(i);
                        //System.out.println("KEY " + i + " = " + key);
                    }
                } while (rs.next());
            } else {
                System.out.println("NO KEYS WERE GENERATED.");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Fails. Verify.", "Error in Query", JOptionPane.ERROR_MESSAGE);
            System.out.println("Query Fail: " + e);
            //e.printStackTrace();
        }
    }

    /**
     * Exec Query in Auth Windows
     *
     * @param db  VaultValuesLoader.DEFAULT_DB
     * @param qry
     */
    public void ExecQuery_WAuth(String db, String qry) {

        System.out.println("Query >> " + qry);
        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://" + VaultValuesLoader.getDefaultHost() +":" + VaultValuesLoader.getJdbcPort() + ";databaseName=" + VaultValuesLoader.getDefaultDBName() + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            System.out.println("# - Statement Created");
            ResultSet rs = stmt.executeQuery(qry);
            System.out.println("# - Statement Created");
                if (rs.next()) {
                    System.out.println("Resultado Query: " + rs.getInt(1));
                }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Closed Connection");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}