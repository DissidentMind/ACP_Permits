package model.db.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_TransactGet {

    private List<String> lstItem = new ArrayList<String>();
    private String idMatriz;
    private String idCom;
    private String adqDate;
    private int idAgency;

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

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }

    public String getAdqDate() {
        return adqDate;
    }

    public void setAdqDate(String adqDate) {
        this.adqDate = adqDate;
    }

    public int getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(int idAgency) {
        this.idAgency = idAgency;
    }
}
