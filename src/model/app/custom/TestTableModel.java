package model.app.custom;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Map;

public class TestTableModel {
    public static TableModel toTableModel(Map<?, ?> map) {

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Key", "Value"}, 0
        );

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
        return model;
    }
}
