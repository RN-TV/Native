package com.pm.rn.utils;

import android.content.Context;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by pm on 17-11-24.
 */

public class FileUtils implements FileConstant {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static String getDir(Context context) {
        return context.getDir(PATCH_FOLDER, Context.MODE_PRIVATE).getAbsolutePath();
    }

    public static String getCacheDir(Context context) {
        return context.getCacheDir().getAbsolutePath();
    }

    private static String getAppFileDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public static String getJsBundleFile(Context context) {
        String appFileDir = getAppFileDir(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(appFileDir).append(File.separator).append(JS_BUNDLE_FILE);
        String jsBundleFile = stringBuffer.toString();
        File file = new File(jsBundleFile);
        if (file.exists()) {
            return jsBundleFile;
        }
        return null;
    }

    public static String getImagePath(Context context) {
        String appFileDir = getAppFileDir(context);
        File imageDir = new File(appFileDir, IMAGE_FOLDER);
        if (!imageDir.exists()) {
            boolean isSuccess = imageDir.mkdir();
            Log.d(TAG, "getImagePath: isSuccess=" + isSuccess);
        }
        return imageDir.getAbsolutePath()+File.separator;
    }

    public static String getZipFile(Context context) {
        return getCacheDir(context) + File.separator + ZIP_FILE;
    }


    public static String getPatchFile(Context context) {
        return getDir(context) + File.separator + FileUtils.PATCH_FILE;
    }

    public static String getPatchImagePath(Context context) {
        return getDir(context) + File.separator + FileUtils.IMAGE_FOLDER+File.separator;
    }

    public boolean isExistsFolder(String path) {
        if (path != null && !path.isEmpty()) {
            File jsBundle = new File(path);
            return jsBundle.exists();
        }
        return false;
    }

    public static boolean createBundleFolder(String path) {
        if (path != null && !path.isEmpty()) {
            File jsBundle = new File(path);
            if (!jsBundle.exists()) {
                return jsBundle.mkdirs();
            }
        }
        return false;
    }

    public static boolean copy() {
        return false;
    }

    /**
     * 解压 ZIP 包
     */
    public static void decompression(String toPath, String fromZipFile) {
        try {
            ZipInputStream inZip = new ZipInputStream(new FileInputStream(fromZipFile));
            ZipEntry zipEntry;
            String szName;
            try {

                while ((zipEntry = inZip.getNextEntry()) != null) {

                    szName = zipEntry.getName();
                    if (zipEntry.isDirectory()) {
                        szName = szName.substring(0, szName.length() - 1);
                        File folder = new File(toPath + File.separator + szName);
                        folder.mkdirs();
                    } else {
                        File file1 = new File(toPath + File.separator + szName);
                        file1.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file1);
                        int len;
                        byte[] buffer = new byte[1024];
                        while ((len = inZip.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                            fos.flush();
                        }
                        fos.close();
                    }
                    Log.d(TAG, "decompression: szName=" + szName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                inZip.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取Assets目录下的bundle文件
     *
     * @return
     */
    public static String getJsBundleFromAssets(Context context) {

        String result = "";
        try {
            InputStream is = context.getAssets().open(FileConstant.JS_BUNDLE_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析SD卡下的bundle文件
     *
     * @param filePath
     * @return
     */
    public static String getJsBundleFromLocal(String filePath) {

        String result = "";
        try {
            InputStream is = new FileInputStream(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将.pat文件转换为String
     *
     * @param patPath 下载的.pat文件所在目录
     * @return
     */
    public static String getStringFromPat(String patPath) {

        FileReader reader = null;
        String result = "";
        try {
            reader = new FileReader(patPath);
            int ch = reader.read();
            StringBuilder sb = new StringBuilder();
            while (ch != -1) {
                sb.append((char) ch);
                ch = reader.read();
            }
            reader.close();
            result = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将图片复制到index.android.bundle所在文件夹下的drawable-mdpi
     *
     * @param srcFilePath
     * @param destFilePath
     */
    public static void copyPatchImgs(String srcFilePath, String destFilePath) {
        Log.d(TAG, "copyPatchImgs: srcFilePath="+srcFilePath+"\n"+"destFilePath="+destFilePath);

        File root = new File(srcFilePath);
        File[] files;
        if (root.exists() && root.listFiles() != null) {
            files = root.listFiles();
            for (File file : files) {
                File oldFile = new File(srcFilePath + file.getName());
                File newFile = new File(destFilePath + file.getName());
                DataInputStream dis = null;
                DataOutputStream dos = null;
                try {
                    dos = new DataOutputStream(new FileOutputStream(newFile));
                    dis = new DataInputStream(new FileInputStream(oldFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                int temp;
                try {
                    while ((temp = dis.read()) != -1) {
                        dos.write(temp);
                    }
                    dis.close();
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除指定File
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        File patFile = new File(filePath);
        if (patFile.exists()) {
            patFile.delete();
        }
    }
}
