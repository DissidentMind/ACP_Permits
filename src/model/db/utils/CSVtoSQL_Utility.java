package model.db.utils;

import gui.controller.init.DefaultsLoader;
import utils.files.FileCSV_Utility;
import utils.files.MkFile_Utility;
import utils.regex.Regex_Utility;
import vault.VaultValuesLoader;

import java.util.ArrayList;
import java.util.List;

public class CSVtoSQL_Utility {

    List<String> listCommunications = new ArrayList<String>();
    List<String> getOthersOrFails = new ArrayList<String>();

    FileCSV_Utility getText = new FileCSV_Utility();
    Regex_Utility getRegexValid = new Regex_Utility();
    MkFile_Utility getMkFile = new MkFile_Utility();

    public CSVtoSQL_Utility(String PATH_FILE_CSV) throws Exception {
        int cIn = 0, cOut = 0, cOth = 0;

        String headerIn = "USE "+ VaultValuesLoader.getDefaultDBName() +"; IF OBJECT_ID ('dbo."+VaultValuesLoader.getDefaultDBName()+"','U') IS NOT NULL DROP TABLE "+VaultValuesLoader.getDefaultDBName()+"; GO";
        String inputIn = "INSERT INTO "+VaultValuesLoader.getDefaultDBName()+"(FOLIO_INTERNO,NOMBRE_DOCTO,DEPENDENCIA_IN,DEPENDENCIA_OUT,LETTER_PATH) VALUES ('";
        String outputOn = "');\n";
        String failStr = "";

        listCommunications = getText.getRowStringFromCSVtoList(PATH_FILE_CSV,0);

        for(int i = 0; i < listCommunications.size(); i++){
            if(getRegexValid.findCurrentIncidenteInString(listCommunications.get(i), DefaultsLoader.getRegexMetaTvdrOut()) != null){
                System.out.println("True => Letter Out: "+getRegexValid.findCurrentIncidenteInString(listCommunications.get(i), DefaultsLoader.getRegexMetaTvdrOut()));
                cOut++;
            }else{
                if(getRegexValid.findCurrentIncidenteInString(listCommunications.get(i), DefaultsLoader.getRegexMetaTvdrIn()) != null){
                    System.out.println("True => Letter IN: "+getRegexValid.findCurrentIncidenteInString(listCommunications.get(i), DefaultsLoader.getRegexMetaTvdrIn()));
                    cIn++;
                }else{
                    cOth++;
                    getOthersOrFails.add(listCommunications.get(i) +"\n");
                }
            }
        }

        System.out.println("Fails Size: "+getOthersOrFails.size());

        for (int j = 0; j < getOthersOrFails.size(); j++) {
            failStr+=getOthersOrFails.get(j);
            //failStr.concat(getOthersOrFails.get(j));
        }

        getMkFile.writeToFile(VaultValuesLoader.getDefaultDowPathFol(), failStr);

        System.out.println("Complete!");
        System.out.println("Letter Outs: "+cOut);
        System.out.println("Letter Ins: "+cIn);
        System.out.println("Letter Others: "+cOth);

    }

}
