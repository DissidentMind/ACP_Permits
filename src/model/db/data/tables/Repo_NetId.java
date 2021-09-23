package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_NetId {

    private List<String> lstItem = new ArrayList<String>();
    private String idCom;
    private int netId;
    private String pathCom;

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

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    public String getPathCom() {
        return pathCom;
    }

    public void setPathCom(String pathCom) {
        this.pathCom = pathCom;
    }
}
