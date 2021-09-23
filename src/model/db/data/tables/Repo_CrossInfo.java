package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_CrossInfo {
    private List<String> lstItem = new ArrayList<String>();
    private String idCruce;
    private int crossType;
    private String detailsCross;

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

    public int getCrossType() {
        return crossType;
    }

    public void setCrossType(int crossType) {
        this.crossType = crossType;
    }

    public String getDetailsCross() {
        return detailsCross;
    }

    public void setDetailsCross(String detailsCross) {
        this.detailsCross = detailsCross;
    }
}
