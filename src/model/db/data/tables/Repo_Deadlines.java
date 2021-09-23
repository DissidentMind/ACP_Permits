package model.db.data.tables;
import java.util.ArrayList;
import java.util.List;

public class Repo_Deadlines {
    private String idMatriz;
    private String vencimiento;
    private String comentarios;
    private int diasRestantes;
    private List<Object> recordList = new ArrayList<Object>();
    private int currentRow;

    public int getCurrentRow() {
        return currentRow;
    }
    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }
    public void setRecordList(List<Object> recordList) {
        this.recordList = recordList;
    }
    public List<Object> getRecordList() {
        return recordList;
    }
    public void setRecordList(Object setIdMatriz) {
        this.recordList.add(setIdMatriz);
    }
    public String getIdMatriz() {
        return idMatriz;
    }
    public void setIdMatriz(String idMatriz) {
        this.idMatriz = idMatriz;
    }
    public String getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public int getDiasRestantes() {
        return diasRestantes;
    }
    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }
}
