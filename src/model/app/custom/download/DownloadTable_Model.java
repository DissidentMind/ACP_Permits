package model.app.custom.download;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DownloadTable_Model extends AbstractTableModel implements Observer {
    private static final String[] columnNames = {
            "Select.", "Id File", "File Name", "Path File" };
    private static final Class[] columnClasses = {
            Checkbox.class, Integer.class, String.class, String.class
    };
    private ArrayList<DownloadItems> downloadList = new ArrayList<DownloadItems>();

    /*public static void setColumnNames(String[] columnNames) {
        //{"File Id", "File Name / Document", "Path"};
        DownloadTable_Model.columnNames = columnNames;
    }*/

    /*public static void setColumnClasses(Class[] columnClasses) {
    //{Integer.class, String.class, String.class}
    DownloadTable_Model.columnClasses = columnClasses;
    }*/

    /*private static final String[] columnNames = {"URL", "Size in MB", "Progress", "Speed in KB/s",
            "Avg Speed in KB/s", "Elapsed Time", "Remaning Time", "Status"};

    private static final Class[] columnClasses = {String.class, String.class,
            JProgressBar.class, String.class, String.class, String.class, String.class, String.class};
    */

    public void addIncidenceInTable(DownloadItems download){
        download.addObserver(this);
        downloadList.add(download);
        fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
    }
    public void clearDownload(int row) {
        downloadList.remove(row);
        fireTableRowsDeleted(row, row);
    }
    public DownloadItems getDownload(int row) {
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
    public void update(Observable o, Object arg) {
        // Fire table row update notification to table.
        fireTableRowsUpdated(downloadList.indexOf(o), downloadList.indexOf(o));
    }
    public Object getValueAt(int row, int col) {
        DownloadItems download = downloadList.get(row);
        switch (col) {
/*            case 0: // URL
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
                return Download.STATUSES[download.getStatus()];*/
            case 1:
                return  download.getIdFile();
            case 2:
                return download.getFileName();
            case 3:
                return download.getFilePath();
        }
        return "";
    }
}