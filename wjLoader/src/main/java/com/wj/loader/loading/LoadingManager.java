package com.wj.loader.loading;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import com.wj.loader.DownLoad.DownLoadJar;
import com.wj.loader.DownLoad.DownLoadListener;
import com.wj.loader.DownLoad.RequestDownLoadAddress;
import com.wj.loader.HttpCenter.HttpListener;
import com.wj.loader.Utils.Constants;
import com.wj.loader.Utils.Kode;

import dalvik.system.DexClassLoader;

/**
 * Created by Administrator on 2017/7/27.
 */

public class LoadingManager {
    private static Context context;
    private static LoadingManager loadingManager = null;
    String jarName = "";
    Class<?> cls;

    public static LoadingManager getInstance() {
        if (loadingManager == null) {
            loadingManager = new LoadingManager();
        }
        return loadingManager;
    }

    private String getUrl(String content) {
        try {
            JSONObject json = new JSONObject(content);
            String result = json.getString("url");
            return result;
        } catch (Exception e) {
            return "";
        }
    }

    public void loading(final Context ctx, final Handler LoadingCallBack) {
        context = ctx;
        RequestDownLoadAddress.getInstance().request(new HttpListener() {
            @Override
            public void result(String result) {
                if (Constants.isOutPut) {
                    System.out.println("======>" + Kode.a(result));
                }
                //String url = getUrl(Kode.a(result));
                //String url = "http://120.76.74.206:8080/youxipj/Download/PaySDK.apk";
                String url = "http://120.76.74.206:8080/youxipj/Download/com.sdk.wj.paysdk_1.0-release.apk";
                jarName = getJarName(url);
                if (Constants.isOutPut) {
                    System.out.println("======>url:" + url);
                    System.out.println("======>jarName:" + jarName);
                }
                new DownLoadJar(ctx, jarName, url, new DownLoadListener() {
                    @Override
                    public void DownLoadState(int state) {
                        cls = LoadDex(context, jarName);
                        if (cls != null) {
                            LoadingCallBack.sendEmptyMessage(1);
                        } else {
                            Log.d("SDK", "cls is null");
                        }
                    }
                }).start();
            }
        });
    }

    private String getJarName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    private String getFilesDir(Context context) {
        if (context != null) {
            return context.getFilesDir().getAbsolutePath();
        }
        return null;
    }

    private Class<?> LoadDex(Context context, String jarName) {
        String dexPath = Constants.dexPath + jarName;
        File codeDir = context.getDir("dex", Context.MODE_PRIVATE);
        String pacageName = Constants.getPackageName();
        if (Constants.isOutPut) {
            Log.i("SDK", "====>dexPath:" + dexPath);
        }

        if (Constants.isOutPut) {
            Log.i("SDK", "====>dexOutputPath:" + codeDir.getAbsolutePath());
        }
        if (Constants.isOutPut) {
            Log.i("SDK", "获取到的包名：" + pacageName);
            Log.i("SDK", "加载jar完成");
        }
        try {
            DexClassLoader calssLoader = new DexClassLoader(dexPath, codeDir.getAbsolutePath(), null,
                    this.getClass().getClassLoader());
            if (calssLoader == null) {
                Log.d("", "calssLoader is null");
                return null;
            } else {
                Log.e("SDK", calssLoader.toString());
                Class<?> clazz = calssLoader.loadClass("com.sdk.wj.paysdk.BMapManager");
                return clazz;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class<?> getClazz() {
        if (cls != null) {
            return cls;
        }
        return null;
    }
}
