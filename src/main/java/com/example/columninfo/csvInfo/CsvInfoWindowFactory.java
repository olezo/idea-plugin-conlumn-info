package com.example.columninfo.csvInfo;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class CsvInfoWindowFactory implements ToolWindowFactory {

    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        CsvInfoWindow myToolWindow = new CsvInfoWindow();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindow, "", false);
        toolWindow.getContentManager().addContent(content);
    }

}