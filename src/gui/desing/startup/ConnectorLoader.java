package gui.desing.startup;

public class ConnectorLoader {

    private Boolean validateExistConnection;
    private Boolean validateExistDb;
    private Boolean validateExistTable;

    private int totalColumnsLog;
    private int totalColumnsDeadLine;

    public Boolean getValidateExistConnection() {
        return validateExistConnection;
    }

    public void setValidateExistConnection(Boolean validateExistConnection) {
        this.validateExistConnection = validateExistConnection;
    }

    public Boolean getValidateExistDb() {
        return validateExistDb;
    }

    public void setValidateExistDb(Boolean validateExistDb) {
        this.validateExistDb = validateExistDb;
    }

    public Boolean getValidateExistTable() {
        return validateExistTable;
    }

    public void setValidateExistTable(Boolean validateExistTable) {
        this.validateExistTable = validateExistTable;
    }

    public int getTotalColumnsLog() {
        return totalColumnsLog;
    }

    public void setTotalColumnsLog(int totalColumnsLog) {
        this.totalColumnsLog = totalColumnsLog;
    }

    public int getTotalColumnsDeadLine() {
        return totalColumnsDeadLine;
    }

    public void setTotalColumnsDeadLine(int totalColumnsDeadLine) {
        this.totalColumnsDeadLine = totalColumnsDeadLine;
    }
}
