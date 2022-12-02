package com.example.columninfo;

import com.example.columninfo.csvInfo.CsvInfoWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

public class TheAction extends AnAction {
    private static final String PLUGIN_WINDOW_ID = "CSV Info";
    private static final String CSV_COLUMNS_DIVIDER = ";";
    private static final String TABLE_ROW_DIVIDER = "__________";

    private static final String COLUMN = "Column";
    private static final String DESCRIPTION = "Description";
    private static final String SYMBOL = "Symbol";
    private static final String UNITS = "Units";
    private Editor editor;

    @Override
    public void actionPerformed(AnActionEvent e) {
        editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = getSelectedText();
        System.out.println("Selected text is: " + selectedText);

        var project = e.getProject();
        drawTable(project, selectedText);
    }

    private void drawTable(Project project, String selectedText) {
        CsvInfoWindow window = getPluginWindow(project);
        var tableModel = window.getModel();

        // clear the data:
        tableModel.setRowCount(0);

        ColumnParser.getColumnsOf(selectedText).stream()
                .map(this::getColumnInfo)
                .forEach(info -> addTo(tableModel, info));
    }

    private ColumnInformation getColumnInfo(String propertyName) {
        propertyName = propertyName.trim();
        var description = PropertyHolder.propertiesUA.getProperty(propertyName.concat(".long"));
        var symbol = PropertyHolder.propertiesUA.getProperty(propertyName.concat(".short"));
        var units = PropertyHolder.propertiesUA.getProperty(propertyName.concat(".units"));

        return new ColumnInformation(propertyName, description, symbol, units);
    }

    private void addTo(DefaultTableModel tableModel, ColumnInformation columnInfo) {
        addToTable(tableModel, COLUMN, columnInfo.getName());
        addToTable(tableModel, DESCRIPTION, columnInfo.getDescription());
        addToTable(tableModel, SYMBOL, columnInfo.getSymbol());
        addToTable(tableModel, UNITS, columnInfo.getUnits());
        addToTable(tableModel, TABLE_ROW_DIVIDER, TABLE_ROW_DIVIDER);
    }

    private void addToTable(DefaultTableModel tableModel, String Units, String columnInfo) {
        if (columnInfo == null || columnInfo.isBlank()) {
            return;
        }
        tableModel.addRow(new String[]{Units, columnInfo});
    }

    private static class ColumnParser {
        public static List<String> getColumnsOf(String str) {
            if (str == null || str.isBlank()) {
                return List.of();
            }

            return List.of(str.split(CSV_COLUMNS_DIVIDER));
        }
    }

    @NotNull
    private CsvInfoWindow getPluginWindow(Project project) {
        var toolWindow = ToolWindowManager.getInstance(project).getToolWindow(PLUGIN_WINDOW_ID);
        var contents = toolWindow.getContentManager().getContent(0);

        return (CsvInfoWindow) contents.getComponent();
    }

    private String getSelectedText() {
        return Optional.ofNullable(editor.getSelectionModel().getSelectedText()).orElse("");
    }
}