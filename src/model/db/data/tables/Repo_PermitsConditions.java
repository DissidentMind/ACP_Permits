package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_PermitsConditions {

    private List<String> lstItem = new ArrayList<String>();
    private int idCondition;
    private String idCross;
    private String conditions;
    private String reqActions;
    private int reqReports;
    private int doneReports;
    private double progress;
    private String conditionsComments;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(int idCondition) {
        this.idCondition = idCondition;
    }

    public String getIdCross() {
        return idCross;
    }

    public void setIdCross(String idCross) {
        this.idCross = idCross;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getReqActions() {
        return reqActions;
    }

    public void setReqActions(String reqActions) {
        this.reqActions = reqActions;
    }

    public int getReqReports() {
        return reqReports;
    }

    public void setReqReports(int reqReports) {
        this.reqReports = reqReports;
    }

    public int getDoneReports() {
        return doneReports;
    }

    public void setDoneReports(int doneReports) {
        this.doneReports = doneReports;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getConditionsComments() {
        return conditionsComments;
    }

    public void setConditionsComments(String conditionsComments) {
        this.conditionsComments = conditionsComments;
    }
}
