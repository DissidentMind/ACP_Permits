package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDAgents {

    private List<String> lstItem = new ArrayList<String>();
    private boolean validRecord = false;
    private String agentName;
    private String emailAgent;
    private String depotAgent;
    private String rolAgent;

    public boolean isValidRecord() {
        return validRecord;
    }

    public void setValidRecord(boolean validRecord) {
        this.validRecord = validRecord;
    }

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getEmailAgent() {
        return emailAgent;
    }

    public void setEmailAgent(String emailAgent) {
        this.emailAgent = emailAgent;
    }

    public String getDepotAgent() {
        return depotAgent;
    }

    public void setDepotAgent(String depotAgent) {
        this.depotAgent = depotAgent;
    }

    public String getRolAgent() {
        return rolAgent;
    }

    public void setRolAgent(String rolAgent) {
        this.rolAgent = rolAgent;
    }
}
