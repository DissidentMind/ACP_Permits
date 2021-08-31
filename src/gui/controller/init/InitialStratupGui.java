package gui.controller.init;

import model.db.Db_Utility;
import vault.VaultValuesLoader;

/**
 * Class that generate first load for apps
 */
public class InitialStratupGui {

    InitialStratupGui() {
        System.out.println("Loading App...");
    }

    public static void loadingDBApp() {
        /*
        Validate connection if exist
        Required Path to Library (VM Argument): -Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\;${env_var:PATH}"
        Required Path to Library (VM Argument): -Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\sqljdbc_8.2\enu\auth\x64"
        Required Path to Library (VM Argument): -Djava.library.path="C:\Users\fabio_rodriguez\OneDrive - TransCanada Corporation\Documents\IT\JODBC\sqljdbc_9.2\enu\auth\x64"
         */
        Db_Utility.TestConnection_JDBC(VaultValuesLoader.getDefaultHost(), VaultValuesLoader.getJdbcPort(), VaultValuesLoader.getDefaultDBName(), VaultValuesLoader.getDefaultTable());


    }


}
