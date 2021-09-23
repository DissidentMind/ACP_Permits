package model.db.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_TransactInfo {
    private List<String> lstItem = new ArrayList<String>();
    private String idMatriz;
    private String idCruce;
    private String fileDate;
    private String validity;
    private String deadLine;
    private int lastTransact;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public String getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(String idMatriz) {
        this.idMatriz = idMatriz;
    }

    public String getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(String idCruce) {
        this.idCruce = idCruce;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public int getLastTransact() {
        return lastTransact;
    }

    public void setLastTransact(int lastTransact) {
        this.lastTransact = lastTransact;
    }
}
