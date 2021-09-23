package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_ClosureStatus {

    private List<String> lstItem = new ArrayList<String>();
    private String idCruce;
    private int closureStatus;
    private String terminationFile;
    private boolean reponseRequire;
    private String closingFile;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public String getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(String idCruce) {
        this.idCruce = idCruce;
    }

    public int getClosureStatus() {
        return closureStatus;
    }

    public void setClosureStatus(int closureStatus) {
        this.closureStatus = closureStatus;
    }

    public String getTerminationFile() {
        return terminationFile;
    }

    public void setTerminationFile(String terminationFile) {
        this.terminationFile = terminationFile;
    }

    public boolean isReponseRequire() {
        return reponseRequire;
    }

    public void setReponseRequire(boolean reponseRequire) {
        this.reponseRequire = reponseRequire;
    }

    public String getClosingFile() {
        return closingFile;
    }

    public void setClosingFile(String closingFile) {
        this.closingFile = closingFile;
    }
}
