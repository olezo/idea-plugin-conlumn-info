package com.example.columninfo.csvInfo;

import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.stream.IntStream;

public class CsvInfoWindow extends SimpleToolWindowPanel {

    private final JPanel jPanel;
    private final DefaultTableModel model;

    public CsvInfoWindow() {
        super(true);

        System.out.println("Building CsvInfoWindow");
        var layout = new GridBagLayout();
        layout.preferredLayoutSize(this);
        jPanel = new JPanel(new VerticalFlowLayout(true, true));

        model = new DefaultTableModel();
        var jTable = new JBTable(model);

        model.addColumn("Key");
        model.addColumn("Value");
        IntStream.range(0, 5).forEach(i -> dummyData());

        JBScrollPane jsp = new JBScrollPane(jTable);
        jPanel.add(jsp);

        setContent(jPanel);
    }

    private void dummyData() {
        model.addRow(new String[]{"Scroll Placeholder", "Value Placeholder"});
        model.addRow(new String[]{"Key Placeholder", "Value Placeholder"});
        model.addRow(new String[]{"Key Placeholder", "Value Placeholder"});
        model.addRow(new String[]{"---", "---"});
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JComponent getContent() {
        return jPanel;
    }
}