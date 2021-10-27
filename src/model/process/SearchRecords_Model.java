package model.process;

import model.app.Download;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Map;

public class SearchRecords_Model extends AbstractTableModel {

    private static String[] columnNames = {"IndexId","File Name","File Path"};
    private static final Class[] columnClasses = {Integer.class,String.class,String.class};

    private final ArrayList<Record> recordList = new ArrayList<Record>();
    protected Map map;
    //protected String[] columnNames;

    public SearchRecords_Model(){
        super();
    }
    public SearchRecords_Model(Map map, int indexId, String fileName, String filePath){
        this();
        setMap(map);
        setColumnNames(""+indexId,fileName,filePath);
    }
    public void setColumnNames(String idFile, String keyName, String valueName) {
        columnNames = new String[]{idFile, keyName, valueName};
    }
    public void setMap(Map _map) {
        map = _map;
    }
    public void addRow(Object item) {
        recordList.add((Record) item);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }
    public void insertRow(int x, Object item) {
        recordList.add((Record) item);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    /*public Record getRecord(int row){
        return  recordList.get(row);
    }
    public void clearRecord(int row){
        recordList.remove(row)
    }
    */

    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public int getRowCount() {
        return map.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Object[] entries = map.entrySet().toArray();
        Map.Entry entry = (Map.Entry) entries[row];
        if (column == 0) {
            return entry.getKey();
        } else if (column == 1) { // column==1
            return entry.getValue();
        } else {
            throw new IndexOutOfBoundsException("MapTableModel provides a 2-column table, column-index " + column + " is illegal.");
        }
    }
}
