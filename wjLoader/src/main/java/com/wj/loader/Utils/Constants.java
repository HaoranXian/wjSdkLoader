package com.wj.loader.Utils;


import android.os.Environment;

/**
 * Created by Administrator on 2016/12/20.
 */

public class Constants {
    public static final boolean isOutPut = true;

    /**
     * 获取服务器地址
     *
     * @return
     */
    public static String getLocationAddress() {
        return "http://120.76.74.206:8090/ExternalPJ/uploadController/getDynamicLoading.do";
//        return "http://192.168.1.:8080/youxipj/pinterface/DownloadAction!getDynamicLoading";
    }

    /**
     * 获取包名
     *
     * @return
     */
    public static String getPackageName() {
        return "com.baidu.BaiduMap";
    }

    /**
     * 获取动态加载时加载的类名
     *
     * @return
     */
    public static String getLoadClassName() {
        return "BMapManager";
    }

    public static String dexPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
}
