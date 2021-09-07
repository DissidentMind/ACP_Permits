package model.app.custom.download;

import model.app.Download;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Map;

public class DownloadMapper extends AbstractTableModel {
    protected Map map;
    protected String[] columnNames;
    private final ArrayList<Download> downloadList = new ArrayList<Download>();

    public DownloadMapper() {
        super();
    }
    public DownloadMapper(Map map) {
        this(map, "Entry", "Value");
    }
    public DownloadMapper(Map map, String keyName, String valueName) {
        this();
        setMap(map);
        setColumnNames(keyName, valueName);
    }

    @Override
    public int getRowCount() {
        return map.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
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

    public String getColumnName(int column) {
        return columnNames[column];
    }
    public void setColumnNames(String keyName, String valueName) {
        //String[] names = {keyName, valueName};
        //columnNames = names;
        columnNames = new String[]{keyName, valueName};
    }
    public Map getMap() {
        return map;
    }
    public void setMap(Map _map) {
        map = _map;
    }

    public void addRow(Object download) {
        //download.addObserver(this);
        downloadList.add((Download) download);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertRow(int x, Object download) {
        //download.addObserver(this);
        downloadList.add((Download) download);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }
}
