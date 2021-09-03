package gui.controller.apache.poi;

import java.util.Map;
import java.util.TreeMap;
/**
 * Class that generate a map <String, RecordPOI> in order to export it and write excel file
 * @author msi-gseries
 *
 */
public class POIMapper {
    Map<String, Object[]> data = new TreeMap<String, Object[]>();
    /**
     * Return Map of Objects
     *
     */
    public Map<String, Object[]> getData() {
        return data;
    }

    /**
     * Add new record to current map
     *
     */
    public void addData(String intIndex,  Object[] newRecord) {
        this.data.put(intIndex, newRecord);
    }
}