package com.upcoding.buy;

import android.app.Application;
import android.util.Log;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.upcoding.buy.utils.MiscUtils;

//import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by Heboot on 16/6/25.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    /**
     * app版本号
     */
    private String version;

    /**
     * token更新的时间戳
     */
    private long uploadTokenUpdateTime;

    /**
     * 七牛的上传token
     */
    private String uploadToken;

    /**
     * 个推的clientId
     */
    private String getuiCID;

    /**
     * 七牛的解析路径
     */
    private String qiniuPrefix;


    @Override
    public void onCreate() {
        super.onCreate();
//        Thread.currentThread().setUncaughtExceptionHandler(new
//                    MyThreadCatch());
        myApplication = this;
        version = MiscUtils.getAppVersion(this);
    }


    public static MyApplication getInstance() {
        return myApplication;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getUploadTokenUpdateTime() {
        return uploadTokenUpdateTime;
    }

    public void setUploadTokenUpdateTime(long uploadTokenUpdateTime) {
        this.uploadTokenUpdateTime = uploadTokenUpdateTime;
    }

    public String getUploadToken() {
        return uploadToken;
    }

    public void setUploadToken(String uploadToken) {
        this.uploadToken = uploadToken;
    }


    private class MyThreadCatch implements Thread.UncaughtExceptionHandler {


        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            Writer w = new StringWriter();
            PrintWriter pw = new PrintWriter(w);
            ex.printStackTrace(pw);
            pw.close();
            Log.e("myex", w.toString());
        }
    }

    public String getGetuiCID() {
        return getuiCID;
    }

    public void setGetuiCID(String getuiCID) {
        this.getuiCID = getuiCID;
    }

}
