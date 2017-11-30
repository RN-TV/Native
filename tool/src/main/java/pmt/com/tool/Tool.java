package pmt.com.tool;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Tool {
    public Tool() {
    }

    public String getPath() {
        String path = Tool.class.getClassLoader().getResource("/").getPath();
        System.out.println("path = " + path);
        return path;
    }

    public static void main(String[] args) {
        String relativelyPath=System.getProperty("user.dir");
        System.out.println("relativelyPath = " + relativelyPath);

        String o = getStringFromPat(relativelyPath+"/bundle/old.bundle");
        String n = getStringFromPat(relativelyPath+"/app/src/main/assets/index.android.bundle");

        // 对比
        diff_match_patch dmp = new diff_match_patch();
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(o, n);

        // 生成差异补丁包
        LinkedList<diff_match_patch.Patch> patch = dmp.patch_make(diffs);

        // 解析补丁包
        String patchStr = dmp.patch_toText(patch);

        try {
            // 将补丁文件写入到某个位置
            Files.write(Paths.get(relativelyPath+"/bundle/patch.pat"), patchStr.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getStringFromPat(String patchPath) {

        FileReader reader = null;
        String result = "";

        try {
            reader = new FileReader(patchPath);
            char[] buf = new char[3];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = reader.read(buf)) != -1) {
                String string = new String(buf, 0, len);
                System.out.println("string = " + string);
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
    }
}


