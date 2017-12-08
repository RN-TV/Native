package com.pm.tool;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * Created by pm on 17-12-8.
 */

public class CustomTask extends DefaultTask {
    /*@TaskAction
    void output() {
        println "oldPatchFile is ${project.patchConfig.oldPatchFile},\nnewPatchFile: ${project.patchConfig.newPatchFile}"
        def oldPatch=project.patchConfig.oldPatchFile
        def newPatch=project.patchConfig.newPatchFile
        def outPatch=project.patchConfig.outPatch
        String o = getStringFromPat(oldPatch);
        String n = getStringFromPat(newPatch);

        // 对比
        diff_match_patch dmp = new diff_match_patch();
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(o, n);

        // 生成差异补丁包
        LinkedList<diff_match_patch.Patch> patch = dmp.patch_make(diffs);

        // 解析补丁包
        String patchStr = dmp.patch_toText(patch);

        try {
            // 将补丁文件写入到某个位置
            Files.write(Paths.get(outPatch), patchStr.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public  String getStringFromPat(String patchPath) {

        FileReader reader = null;
        String result = "";

        try {
            reader = new FileReader(patchPath);
            char[] buf = new char[3];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = reader.read(buf)) != -1) {
                String string = new String(buf, 0, len);
//                System.out.println("string = " + string);
                sb.append(string);
                result = sb.toString();
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }*/
}
