package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_BDUIncidences {

    private List<String> lstItem = new ArrayList<String>();
    private int idIncidences;
    private String idCross;
    private int idProject;
    private String issueInfo;
    private String actionIssue;
    private String issueType;
    private int hardCopy;
    private String classification;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getIdIncidences() {
        return idIncidences;
    }

    public void setIdIncidences(int idIncidences) {
        this.idIncidences = idIncidences;
    }

    public String getIdCross() {
        return idCross;
    }

    public void setIdCross(String idCross) {
        this.idCross = idCross;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getIssueInfo() {
        return issueInfo;
    }

    public void setIssueInfo(String issueInfo) {
        this.issueInfo = issueInfo;
    }

    public String getActionIssue() {
        return actionIssue;
    }

    public void setActionIssue(String actionIssue) {
        this.actionIssue = actionIssue;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public int getHardCopy() {
        return hardCopy;
    }

    public void setHardCopy(int hardCopy) {
        this.hardCopy = hardCopy;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
