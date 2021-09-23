package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDConstructionStatus {

    private List<String> lstItem = new ArrayList<String>();
    private int ddCont;
    private String constStatus;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getDdCont() {
        return ddCont;
    }

    public void setDdCont(int ddCont) {
        this.ddCont = ddCont;
    }

    public String getConstStatus() {
        return constStatus;
    }

    public void setConstStatus(String constStatus) {
        this.constStatus = constStatus;
    }
}
