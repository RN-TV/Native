# 工程介绍：
- bundle/patch.pat:需要更新的插件
- bundle/drawable-mdpi:需要跟新的图片
- bundle/old.bundle：上一次打包的index.android.bundle文件
- bundle/bundle.zip:是需要更新的插件和图片的压缩包,最终上传热更新服务器的文件.
- tool:生成差分包的Java工具
- img:js页面图片
- json:js页面数据源
- js:ReactNative的js端源码

#### 打包bundle命令：
```
react-native bundle --platform android --dev false --entry-file index.android.js --bundle-output app/src/main/assets/index.android.bundle --assets-dest app/src/main/res/
```
> 每一次打包前需要手动把上一次的index.android.bundle文件从assets目录拷贝到bundle目录,并且重命名为old.bundle

#### 生成差分包：
> 运行Tool类的main函数,生成的差分包输出到bundle/patch.apt

> 如果有图片更新(替换或新增加)手动把打包出来的图片放入bundle/drawable-mdpi目录