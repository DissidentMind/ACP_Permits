package model.app.custom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ResultSearchItem {
    public int idFile;
    public String fileName;
    public String pathFile;

    public ResultSearchItem(int idFile, String fileName, String pathFile){
        this.idFile = idFile;
        this.fileName = fileName;
        this.pathFile = pathFile;
    }

    public static void addRowToJTable(JTable jtable, ArrayList<ResultSearchItem> list){
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        Object rowData[] = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            rowData[0] = list.get(i).idFile;
            rowData[1] = list.get(i).fileName;
            rowData[2] = list.get(i).pathFile;
            model.addRow(rowData);
        }

        jtable.setModel(model);

    }

}
