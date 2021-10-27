package model.process;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Observable;

public class Record{
    public int indexItem;
    public  String fileName;
    public String pathFileName;
    public static ArrayList<Record> recordsList = new ArrayList<Record>();

    public Record(int indexItem, String fileName, String pathFileName){
        this.indexItem = indexItem;
        this.fileName = fileName;
        this.pathFileName = pathFileName;
    }

    public static ArrayList<Record> getRecordsList() {
        return recordsList;
    }
}
