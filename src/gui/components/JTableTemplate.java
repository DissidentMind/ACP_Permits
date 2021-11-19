package gui.components;

import model.component.table.JTableModelDesign;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Hashtable;

public class JTableTemplate extends JTable{

    //CellEditorTemplate weirdRenderer = new CellEditorTemplate(this);
    Hashtable rowsToPaint = new Hashtable(1);

    private static final String[] COLUMN_NAMES = {
            "Index",
            "File Name",
            "File Path",
            "Comments",
            "Selection"
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

    JTableTemplate(){
        //----------------------------------------- EDITOR --------------------------
        JTableModelDesign templatesTableModel = new JTableModelDesign();
        this.setModel(templatesTableModel);

        //RowSelectionRenderer rowRender = new RowSelectionRenderer();
        //this.getColumnModel().getColumn(2).setCellRenderer(rowRender);
    //----------------------------------------- EDITOR --------------------------

        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent e) {
                  //getColumnModel().getColumn(3).
                  System.out.println("Event: "+getSelectedRow());
              }
          });

        //this.getSelectedRow().addListSelectionListener(new RenderComponent());

        this.getTableHeader().setBackground(new Color(42,94,146));
        this.getTableHeader().setForeground(Color.white);
        Font headerFont = new Font("Verdana", Font.BOLD, 12);
        this.getTableHeader().setFont(headerFont);
        this.getTableHeader().setBorder(new BevelBorder(2));
        this.getTableHeader().setResizingAllowed(true);

        this.setGridColor(new Color(185,185,185));
        this.setCellSelectionEnabled(true);
        this.setUI(ui);

        //this.setSelectionBackground(new Color(189,204,218));
        this.setSelectionBackground(new Color(186,207,229));
        this.setBackground(new Color(241,244,247));

        //rowSelectionAllowed = true;
        this.getColumnModel().getColumn(0).setPreferredWidth(20);
        this.getColumnModel().getColumn(1).setPreferredWidth(140);
        this.getColumnModel().getColumn(2).setPreferredWidth(740);
        this.getColumnModel().getColumn(3).setPreferredWidth(120);
        this.getColumnModel().getColumn(4).setPreferredWidth(15);



        this.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setColumnSelectionAllowed(false);
        this.setRowHeight(getPreferredSize().height-60);

        this.setAutoCreateRowSorter(true);
        //this.setBorder(new BevelBorder(2));


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.LEFT );

        for(int x=0;x<this.getRowCount()-1;x++){
            this.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }

        //Set Renderer
        //this.setDefaultRenderer(Object.class, new RenderComponent());
        //this.setVisible(true);
    }

/*
    public void dumpFillInTable(){
        ImageIcon p = new ImageIcon("view/assets/avatar-h.png");
        this.setValueAt(p, 0, 0);

        for (int i = 0; i < 10; i++) {
            this.setRowHeight(i, this.getRowHeight(i) + 10);
        }

        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            TableColumn column = this.getColumnModel().getColumn(i);
            column.setPreferredWidth(column.getPreferredWidth() + 80);
        }
    }

    public void addRowToPaint(int row, Color bgColor){
        rowsToPaint.put(new Integer(row), bgColor);
        this.repaint();// you need to repaint the table for each you put in the hashtable.
    }

    public Color getRowToPaint(int row){
        Color bgColor = (Color)rowsToPaint.get(new Integer(row));
        return (bgColor != null)?bgColor:getBackground();
    }

    public  void renderColumns(TableCellRenderer cellRender){
        for(int i=0; i<this.getModel().getColumnCount(); i++){
            renderColumn(this.getColumnModel().getColumn(i), cellRender);
        }
    }

    public void renderColumn(TableColumn col, TableCellRenderer cellRender){
        try{
            col.setCellRenderer(cellRender);
        }catch(Exception e){System.err.println("Error rendering column: [HeaderValue]: "+col.getHeaderValue().toString()+" [Identifier]: "+col.getIdentifier().toString());}
    }

    public TableCellRenderer getCellRenderer(int row, int column) {
        if ((row == 0) && (column == 0)) {
            return weirdRenderer;
        }
        return super.getCellRenderer(row, column);
    }*/
}
