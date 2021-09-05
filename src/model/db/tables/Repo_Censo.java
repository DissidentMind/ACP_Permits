package model.db.tables;

public class Repo_Censo {

    private String idMatriz;
    private String idCruce;
    private String descripcionPermiso;
    private String dependencia;
    private String tramite;
    private String folioPermiso;
    private String tipoPermiso;
    private String detalle;
    private Boolean flagT;

    public String getIdMatriz() {
        return idMatriz;
    }

    public void setIdMatriz(String idMatriz) {
        this.idMatriz = idMatriz;
    }

    public String getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(String idCruce) {
        this.idCruce = idCruce;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getFolioPermiso() {
        return folioPermiso;
    }

    public void setFolioPermiso(String folioPermiso) {
        this.folioPermiso = folioPermiso;
    }

    public String getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(String tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Boolean getFlagT() {
        return flagT;
    }

    public void setFlagT(Boolean flagT) {
        this.flagT = flagT;
    }
}

