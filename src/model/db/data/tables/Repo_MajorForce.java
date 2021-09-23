package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_MajorForce {

    private List<String> lstItem = new ArrayList<String>();
    private int idFM;
    private String idCross;
    private String eventoFM;
    private String deadlineDateFM;
    private String statusFM;
    private String actionFM;
    private String responsibleFM;
    private String followingFM;
    private String commentsFM;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getIdFM() {
        return idFM;
    }

    public void setIdFM(int idFM) {
        this.idFM = idFM;
    }

    public String getIdCross() {
        return idCross;
    }

    public void setIdCross(String idCross) {
        this.idCross = idCross;
    }

    public String getEventoFM() {
        return eventoFM;
    }

    public void setEventoFM(String eventoFM) {
        this.eventoFM = eventoFM;
    }

    public String getDeadlineDateFM() {
        return deadlineDateFM;
    }

    public void setDeadlineDateFM(String deadlineDateFM) {
        this.deadlineDateFM = deadlineDateFM;
    }

    public String getStatusFM() {
        return statusFM;
    }

    public void setStatusFM(String statusFM) {
        this.statusFM = statusFM;
    }

    public String getActionFM() {
        return actionFM;
    }

    public void setActionFM(String actionFM) {
        this.actionFM = actionFM;
    }

    public String getResponsibleFM() {
        return responsibleFM;
    }

    public void setResponsibleFM(String responsibleFM) {
        this.responsibleFM = responsibleFM;
    }

    public String getFollowingFM() {
        return followingFM;
    }

    public void setFollowingFM(String followingFM) {
        this.followingFM = followingFM;
    }

    public String getCommentsFM() {
        return commentsFM;
    }

    public void setCommentsFM(String commentsFM) {
        this.commentsFM = commentsFM;
    }
}
