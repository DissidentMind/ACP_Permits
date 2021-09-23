package model.db.data.tables;

import java.util.ArrayList;
import java.util.List;

public class Repo_GeoInfo {
    private List<String> lstItem = new ArrayList<String>();

    private int idGeo;
    private String idCruce;
    private double coordX;
    private double coordY;
    private String spread;

    public List<String> getLstItem() {
        return lstItem;
    }

    public int getIdGeo() {
        return idGeo;
    }

    public void setIdGeo(int idGeo) {
        this.idGeo = idGeo;
    }

    public String getIdCruce() {
        return idCruce;
    }

    public void setIdCruce(String idCruce) {
        this.idCruce = idCruce;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }
}
