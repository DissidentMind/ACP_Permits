package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDPermitsStatus {

    private List<String> lstItem = new ArrayList<String>();
    private int ddStat;
    private String permitStatus;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getDdStat() {
        return ddStat;
    }

    public void setDdStat(int ddStat) {
        this.ddStat = ddStat;
    }

    public String getPermitStatus() {
        return permitStatus;
    }

    public void setPermitStatus(String permitStatus) {
        this.permitStatus = permitStatus;
    }
}
