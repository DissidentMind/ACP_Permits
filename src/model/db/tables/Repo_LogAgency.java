package model.db.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_LogAgency {

    private List<String> lstItem = new ArrayList<String>();
    private int idVisit;
    private int idAgency;
    private String visitSubject;
    private int idContact;
    private String visitDate;
    private String conmmentsLog;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getIdVisit() {
        return idVisit;
    }

    public void setIdVisit(int idVisit) {
        this.idVisit = idVisit;
    }

    public int getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(int idAgency) {
        this.idAgency = idAgency;
    }

    public String getVisitSubject() {
        return visitSubject;
    }

    public void setVisitSubject(String visitSubject) {
        this.visitSubject = visitSubject;
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getConmmentsLog() {
        return conmmentsLog;
    }

    public void setConmmentsLog(String conmmentsLog) {
        this.conmmentsLog = conmmentsLog;
    }
}
