package com.pm.tool;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by pm on 17-12-8.
 */

public class Tool implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("hello gradle plugin!");
        project.getExtensions().create("patchConfig", ToolExtension.class);
    }
}
