package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_Agency {
    private List<String> lstItem = new ArrayList<String>();
    private int idAgency;
    private String idCross;
    private String agencyDetails;
    private String agencyAddress;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(int idAgency) {
        this.idAgency = idAgency;
    }

    public String getIdCross() {
        return idCross;
    }

    public void setIdCross(String idCross) {
        this.idCross = idCross;
    }

    public String getAgencyDetails() {
        return agencyDetails;
    }

    public void setAgencyDetails(String agencyDetails) {
        this.agencyDetails = agencyDetails;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }
}
