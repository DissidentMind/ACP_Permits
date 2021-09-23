package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDPermitsClose {

    private List<String> lstItem = new ArrayList<String>();
    private int ddClose;
    private String permitClose;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getDdClose() {
        return ddClose;
    }

    public void setDdClose(int ddClose) {
        this.ddClose = ddClose;
    }

    public String getPermitClose() {
        return permitClose;
    }

    public void setPermitClose(String permitClose) {
        this.permitClose = permitClose;
    }
}
