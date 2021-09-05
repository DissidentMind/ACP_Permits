package model.app.custom;

import model.app.Download;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DownloadTable_Model extends AbstractTableModel implements Observer {

    private static final String[] columnNames = {"File Id", "File Name / Document", "Path"};
    private static final Class[] columnClasses = {Integer.class, String.class, String.class};
    private final ArrayList<Download> downloadList = new ArrayList<Download>();

    public Download getDownload(int row) {
        return downloadList.get(row);
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    public int getRowCount() {
        return downloadList.size();
    }

    public void addRecord(Download download) {
        download.addObserver(this);
        downloadList.add(download);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void clearDownload(int row) {
        downloadList.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void update(Observable o, Object arg) {
        int index = downloadList.indexOf(o);
        // Fire table row update notification to table.
        fireTableRowsUpdated(index, index);
    }

    public Object getValueAt(int row, int col) {
        Download download = downloadList.get(row);
        switch (col) {
            case 0: // URL
                return download.getUrl();
            case 1: // Size
                long size = download.getSize();
                return (size == -1) ? "" : Float.toString((float) size / 1048576);
            case 2: // Progress
                return new Float(download.getProgress());
            case 3: //Speed
                return download.getSpeed();
            case 4: //Avg Speed
                return download.getAvgSpeed();
            case 5: //Elapsed Time
                return download.getElapsedTime();
            case 6: //Remaining Time
                return download.getRemainingTime();
            case 7: // Status
                return Download.STATUSES[download.getStatus()];
        }
        return "";
    }
}
