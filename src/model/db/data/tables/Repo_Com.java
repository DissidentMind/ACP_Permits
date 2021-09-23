package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_Com {

    private List<String> lstItem = new ArrayList<String>();
    private String idCom;
    private String subjectCom;
    private String folioCom;
    private String dateCom;
    private int ownerCom;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public String getIdCom() {
        return idCom;
    }

    public void setIdCom(String idCom) {
        this.idCom = idCom;
    }

    public String getSubjectCom() {
        return subjectCom;
    }

    public void setSubjectCom(String subjectCom) {
        this.subjectCom = subjectCom;
    }

    public String getFolioCom() {
        return folioCom;
    }

    public void setFolioCom(String folioCom) {
        this.folioCom = folioCom;
    }

    public String getDateCom() {
        return dateCom;
    }

    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }

    public int getOwnerCom() {
        return ownerCom;
    }

    public void setOwnerCom(int ownerCom) {
        this.ownerCom = ownerCom;
    }
}
