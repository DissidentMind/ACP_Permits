package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_ReportsConditions {

    private List<String> lstItem = new ArrayList<String>();
    private int idReport;
    private int idCondition;
    private String idCom;
    private String complianceDate;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public int getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(int idCondition) {
        this.idCondition = idCondition;
    }

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }

    public String getComplianceDate() {
        return complianceDate;
    }

    public void setComplianceDate(String complianceDate) {
        this.complianceDate = complianceDate;
    }
}
