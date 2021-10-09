package model.db.data.processor;

import java.sql.Connection;
import java.sql.SQLException;

public class TestStructureArray_Agents {

    private String [][] itemsArray;
    private int rowSize;
    private int colSize;

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    public void setColSize(int colSize) {
        this.colSize = colSize;
    }

    public String[][] getItemsArray() {
        return itemsArray;
    }

    public void addItemsToArray(String valueInput){
        if(this.getRowSize() != 0 || this.getColSize() != 0){
            for (int i = 0; i < this.getRowSize(); i++) {
                for (int j = 0; j < this.getColSize(); j++) {
                    if(itemsArray[i][j]==null){
                        itemsArray[i][j] = valueInput;
                    }
                }
            }
        }else{
            System.out.println("Size 0 in array");
        }
    }

    public void replacerArrayAsNull(String[][] array){
        for (int i = 0; i < this.getRowSize(); i++) {
            for (int j = 0; j < this.getColSize(); j++) {
                if(itemsArray[i][j].equals("null")){
                    itemsArray[i][j] = null;
                }
            }
        }
    }

    public void

    TestStructureArray_Agents(
            String verifyQry, String insertQry,
            String pathFile, int currentTab,
            Connection currentConnection,
            int totalColumns, int totalRows
    ) throws ClassNotFoundException, SQLException{



    }


}
