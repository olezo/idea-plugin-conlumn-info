package com.example.columninfo;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

public class MyStartupActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        if (!project.isInitialized()) {
            System.out.println("Loading... Wait");
            return;
        }
        System.out.println("Startup activity is loading");

        PropertyHolder.load(project);

        System.out.println("Startup activity is loaded");
    }

    // PluginManagerCore.disablePlugin()
    // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360009256179-Method-to-disable-plugin-when-requirements-aren-t-met
}
