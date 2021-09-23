package model.db.data.dictionary;

import java.util.ArrayList;
import java.util.List;

public class Repo_DDCompanies {
    private List<String> lstItem = new ArrayList<String>();
    private int ddCompany;
    private String companyName;
    private String companyDesc;
    private String companyType;

    public List<String> getLstItem() {
        return lstItem;
    }

    public void setLstItem(List<String> lstItem) {
        this.lstItem = lstItem;
    }

    public int getDdCompany() {
        return ddCompany;
    }

    public void setDdCompany(int ddCompany) {
        this.ddCompany = ddCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
