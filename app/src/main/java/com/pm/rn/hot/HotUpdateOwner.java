package com.pm.rn.hot;

import android.content.Context;
import android.util.Log;

import com.pm.rn.utils.FileConstant;
import com.pm.rn.utils.FileUtils;
import com.pm.rn.utils.SpUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

/**
 * Created by pm on 17-11-28.
 */

public class HotUpdateOwner {
    private static final String TAG = HotUpdateOwner.class.getSimpleName();
    private boolean mDownLoadSuccessful = false;
    private Context mContext;

    public HotUpdateOwner(Context context) {
        this.mContext = context;
        //
        if (checkVersion()) {
            // TODO: 17-11-28
            //DownLoad
            mDownLoadSuccessful = true;
        }

        if (mDownLoadSuccessful) {
            String patchPath = FileUtils.getDir(mContext);
            Log.d(TAG, "HotUpdateOwner: patchPath=" + patchPath);
            FileUtils.decompression(patchPath, FileUtils.getZipFile(mContext));
        }

        boolean isFrist = SpUtils.get(mContext);
        boolean existsFile = FileUtils.isExistsFile(FileUtils.getJsBundleFilePath(mContext));
        if (isFrist && !existsFile) {
            mergePatAndAsset();
            SpUtils.put(mContext);
        } else {
            mergePatAndBundle();
        }
    }

    private boolean checkVersion() {
        return true;
    }


    /**
     * 与Asset资源目录下的bundle进行合并
     */
    private void mergePatAndAsset() {

        // 1.解析Asset目录下的bundle文件
        String assetsBundle = FileUtils.getJsBundleFromAssets(mContext);
        // 2.解析bundle当前目录下.pat文件字符串
        String patcheStr = FileUtils.getStringFromPat(FileUtils.getPatchFile(mContext));
        // 3.合并
        merge(patcheStr, assetsBundle);
        // 4.添加图片
        FileUtils.copyPatchImgs(FileUtils.getPatchImagePath(mContext), FileUtils.getImagePath(mContext));
        // 5.删除pat
        FileUtils.deleteFile(FileUtils.getPatchFile(mContext));
    }

    /**
     * 与内部存储的bundle进行合并
     */
    private void mergePatAndBundle() {

        // 1.解析sd卡目录下的bunlde
        String assetsBundle = FileUtils.getJsBundleFromLocal(FileUtils.getJsBundleFile(mContext));
        // 2.解析最新下发的.pat文件字符串
        String patcheStr = FileUtils.getStringFromPat(FileUtils.getPatchFile(mContext));
        // 3.合并
        merge(patcheStr, assetsBundle);
        // 4.添加图片
        FileUtils.copyPatchImgs(FileUtils.getPatchImagePath(mContext), FileUtils.getImagePath(mContext));
        // 5.删除本次下发的更新文件
//        FileUtils.traversalFile(FileConstant.FUTURE_JS_PATCH_LOCAL_FOLDER);
    }

    /**
     * 合并,生成新的bundle文件
     */
    private void merge(String patchStr, String bundle) {

        // 3.初始化 dmp
        diff_match_patch dmp = new diff_match_patch();
        // 4.转换pat
        LinkedList<diff_match_patch.Patch> patchs = (LinkedList<diff_match_patch.Patch>) dmp.patch_fromText(patchStr);
        // 5.pat与bundle合并，生成新的bundle
        Object[] bundleArray = dmp.patch_apply(patchs, bundle);
        // 6.保存新的bundle文件
        try {
            Writer writer = new FileWriter(FileUtils.getJsBundleFilePath(mContext));
            String newBundle = (String) bundleArray[0];
            writer.write(newBundle);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
