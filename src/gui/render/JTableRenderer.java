package gui.render;

import model.process.Record;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class JTableRenderer {

    public static void updateJTableModelRecord(JTable customViewJTable){
        DefaultTableModel model = (DefaultTableModel) customViewJTable.getModel();
        ArrayList<Record> recordLst = Record.getRecordsList();
        Object rowData[] = new Object[recordLst.size()];

        for (int i = 0; i < recordLst.size(); i++) {
            rowData[0] = recordLst.get(i).indexItem;
            rowData[1] = recordLst.get(i).fileName;
            rowData[2] = recordLst.get(i).pathFileName;
            model.addRow(rowData);
        }
    }

}
