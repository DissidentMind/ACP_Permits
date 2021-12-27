package gui.components;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.process.Download;

public class JTableTemplate_Download extends AbstractTableModel implements Observer {
    private final ArrayList<Download> downloadList = new ArrayList<Download>();

    private static final String[] columnNames = {"URL", "Size in MB", "Progress", "Speed in KB/s",
            "Avg Speed in KB/s", "Elapsed Time", "Remaning Time", "Status"};

    private static final Class[] columnClasses = {String.class, String.class,
            JProgressBar.class, String.class, String.class, String.class, String.class, String.class};

    public void addDownload(Download download) {
        download.addObserver(this);
        downloadList.add(download);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public Download getDownload(int row) {
        return downloadList.get(row);
    }

    public void clearDownload(int row) {
        downloadList.remove(row);
        fireTableRowsDeleted(row, row);
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public int getRowCount() {
        return downloadList.size();
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    @Override
    public void update(Observable observable, Object o) {

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
                return Download.STATUS_DOWNLOAD[download.getStatus()];
        }
        return "";
    }
}
