package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_ConstructionStatus {

    private List<String> lstItem = new ArrayList<String>();
    private String idCruce;
    private int constructionStatus;

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

    public int getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(int constructionStatus) {
        this.constructionStatus = constructionStatus;
    }
}
