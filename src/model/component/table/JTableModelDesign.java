package model.component.table;

import javax.swing.table.AbstractTableModel;

public class JTableModelDesign extends AbstractTableModel {
    private static final int COLUMN_INDEX_CHECK_BOX = 4;
    private static final String[] COLUMN_NAMES = {
            "Index",
            "File Name",
            "File Path",
            "Comments",
            "Selection"
    };

    private static final Class[] columnClasses = {
            Integer.class,
            String.class,
            String.class,
            String.class,
            Boolean.class
    };

    private static final Object[][] data = {
            {1,"Kathy", "Smith",
                    "Snowboarding", false},
            {2,"John", "Doe",
                    "Rowing", true},
            {3,"Sue", "Black",
                    "Knitting",false},
            {4,"Jane", "White",
                    "Speed reading", true},
            {5,"Joe", "Brown",
                    "Pool",false}
    };

    private static boolean[] editable = {
            false, false, false, false, true
    };

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * Method Override Values Flag Selection
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //     super.setValueAt(aValue, rowIndex, columnIndex); by default empty implementation is not necesary if direct parent is AbstractTableModel
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);// notify listeners
    }

    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public Class<?> getColumnClass(int column){
        switch(column){
            case 0: return Integer.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return Boolean.class;
            default: return String.class;
        }
    }

    /**
     * Model is read-only. This can be do it editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == COLUMN_INDEX_CHECK_BOX;
    }

    @Override
    public Object getValueAt(int row, int column) {
        //Record download = dataList.get(row);
        switch (column) {
            case 0:
                return data[row][column];
            case 1:
                return data[row][column];
            case 2:
                return data[row][column];
            case 3:
                return data[row][column];
            case 4:
                return data[row][column];

        }
        return "";
    }
}