package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDProjects {

    private List<String> lstItem = new ArrayList<String>();
    private int ddProy;
    private String projectName;
    private String projectDesc;
    private double longProject;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getDdProy() {
        return ddProy;
    }

    public void setDdProy(int ddProy) {
        this.ddProy = ddProy;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public double getLongProject() {
        return longProject;
    }

    public void setLongProject(double longProject) {
        this.longProject = longProject;
    }
}
