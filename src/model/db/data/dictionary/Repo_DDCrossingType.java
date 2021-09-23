package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDCrossingType {

    private List<String> lstItem = new ArrayList<String>();
    private int ddType;
    private String constructionType;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getDdType() {
        return ddType;
    }

    public void setDdType(int ddType) {
        this.ddType = ddType;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }
}
