package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_Permits {

    private List<String> lstItem = new ArrayList<String>();
    private String idCruce;
    private String permitName;
    private String permitDescription;
    private int idAgency;
    private int idMunicipality;
    private int idState;
    private String deptoString;

    public List<String> getLstItem() {
        return lstItem;
    }

    public String getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(String idCruce) {
        this.idCruce = idCruce;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    public String getPermitDescription() {
        return permitDescription;
    }

    public void setPermitDescription(String permitDescription) {
        this.permitDescription = permitDescription;
    }

    public int getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(int idAgency) {
        this.idAgency = idAgency;
    }

    public int getIdMunicipality() {
        return idMunicipality;
    }

    public void setIdMunicipality(int idMunicipality) {
        this.idMunicipality = idMunicipality;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public String getDeptoString() {
        return deptoString;
    }

    public void setDeptoString(String deptoString) {
        this.deptoString = deptoString;
    }
}
