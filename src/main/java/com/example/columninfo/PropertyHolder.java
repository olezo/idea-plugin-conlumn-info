package com.example.columninfo;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.ProjectScope;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropertyHolder {
    public static Properties propertiesUA;
    public static Properties propertiesEN;

    public static void load(@NotNull Project project) {
        try {
            propertiesUA = getProperties(project, "columns_uk.properties");
            propertiesEN = getProperties(project, "columns.properties");
        } catch (Exception e) {
            System.out.println("Failed to load properties");
            e.printStackTrace();
        }
    }

    private static Properties getProperties(@NotNull Project project, String propertyFileName) throws IOException {
        String textFromFile = loadFileContent(project, propertyFileName);

        Properties properties = new Properties();
        properties.load(new StringReader(textFromFile));
        return properties;
    }

    private static String loadFileContent(@NotNull Project project, String propertyFileName) {
        var arr = FilenameIndex.getFilesByName(project, propertyFileName, ProjectScope.getProjectScope(project));

        var file = arr[0];
        return file.getContainingFile().getOriginalFile().getText();
    }
}
