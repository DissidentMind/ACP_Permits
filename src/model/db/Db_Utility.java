package model.db;

import vault.VaultValuesLoader;

import javax.swing.*;
import java.sql.*;

//Arguments
//-Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\;${env_var:PATH}"

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
            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";user=" + user + ";password=" + pass + ";databaseName=" + db + "";
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
            String server = "localhost\\sqlexpress";
            int port = 1433;

            Class.forName(VaultValuesLoader.JDBC_SQLSERVER_DRIVER);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";user=" + user + ";password=" + pass + ";databaseName=" + db + "";
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

    public void TestConnection_JDBC_WindowsAuthentification() {

        try {
            Class.forName(VaultValuesLoader.JDBC_SQLSERVER_DRIVER);
            System.out.println("Driver Loaded");
            String server = "localhost\\sqlexpress";
            //String server = "jdbc:sqlserver://";
            //String user = "TCPL\fabio_rodriguez";;
            //String db = "TGNH_Permits";

            int port = 1433;
            String db = VaultValuesLoader.DEFAULT_DB;

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + db + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            System.out.println("# - Statement Created");
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM CAT_TIPO_CRUCE");
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

    public static void TestConnection_JDBC(String server, int port, String db, String table) {
        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
            System.out.println("Driver Loaded");
            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + db + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            System.out.println("# - Statement Created");
            System.out.println("# - Verify Table Permits...");
            ResultSet rs = stmt.executeQuery("SELECT OBJECT_ID FROM sys.objects WHERE name = '" + table + "';");
            if (rs == null) {
                rs.close();
                JOptionPane.showMessageDialog(null, "Database connection is available but table was not found. Verify.", "Table Doesn't Exist", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            System.out.println("# - Table was Found... Starting GUI");
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

    private static void displayRow(String title, ResultSet rs) throws SQLException {
        System.out.println(title);
        while (rs.next()) {
            System.out.println(rs.getString("ID_MATRIZ") + " : " + rs.getString("ID_PERMISO"));
        }
    }

    public Connection startConnection_WAuth(String db) {

        int port = 1433;
        Connection conTemp = null;

        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
            System.out.println("Driver Loaded");
            String server = "localhost\\sqlexpress";
            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + db + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            conTemp = con;
            //Statement stmt = con.createStatement();
            //		  stmt.executeQuery("SET NOCOUNT ON");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return conTemp;
    }


    public void executeInsertWithKeys(Connection con, String query) {
        try {
            String SQL = query;
            Statement stmt = con.createStatement();
            int count = stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
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
            //System.out.println("Query Fail: "+query);
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

        int port = 1433;

        try {
            Class.forName(VaultValuesLoader.sqlSerClass);
            System.out.println("Driver Loaded");
            String server = "localhost\\sqlexpress";
            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + db + ";integratedSecurity=true";
            Connection con = DriverManager.getConnection(jdbcUrl);
            //System.out.println("# - Connection Obtanied: ");
            Statement stmt = con.createStatement();
            //System.out.println("# - Statement Created");
            ResultSet rs = stmt.executeQuery(qry);
            //System.out.println("# - Statement Created");

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
