package com.example.columninfo.csvInfo;

import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.table.JBTable;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CsvInfoWindowReservedCopy extends SimpleToolWindowPanel {

    private final JPanel jPanel;
    private final DefaultTableModel model;

    public CsvInfoWindowReservedCopy() {
        super(true);

        System.out.println("Building CsvInfoWindow");
        jPanel = new JPanel(new VerticalLayout());
        model = new DefaultTableModel();
        var jTable = new JBTable(model);

        model.addColumn("Key");
        model.addColumn("Value");
        model.addRow(new String[] {"Key Placeholder", "Value Placeholder"});
        model.addRow(new String[] {"Key Placeholder", "Value Placeholder"});
        model.addRow(new String[] {"Key Placeholder", "Value Placeholder"});
        model.addRow(new String[] {"---", "---"});

        jPanel.add(jTable);

        setContent(jPanel);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JComponent getContent() {
        return jPanel;
    }
}