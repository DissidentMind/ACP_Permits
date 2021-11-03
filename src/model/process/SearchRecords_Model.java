package model.process;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchRecords_Model extends AbstractTableModel{

    private static final String[] columnNames = {
            "Index", "File Name", "File Path", "Selector"};
    private static final Class[] columnClasses = {
            Integer.class, String.class,
            String.class, Boolean.class};

    private final ArrayList<Record> downloadList = new ArrayList<Record>();


    public void addNewMatchResult(Record rec){
        downloadList.add(rec);
        // Fire table row insertion notification to table.
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public Record getRecord(int row){
        return downloadList.get(row);
    }

    public void clearRecord(int row){
        downloadList.remove(row);
        // Fire table row deletion notification to table.
        fireTableRowsDeleted(row, row);
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return downloadList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Record download = downloadList.get(row);
        switch (column) {
            case 0:
                return download.indexId;
            case 1:
                return download.fileName;
            case 2:
                return download.filePath;
            case 3:
                return download.selectedId;
        }
        return "";
    }
}