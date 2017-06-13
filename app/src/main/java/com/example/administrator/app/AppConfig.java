package com.example.administrator.app;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class AppConfig {
    public final static String APP_CONFIG = "config";
    //默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "MVPDemoApplication"
            + File.separator;
    //默认下载图片的路径
    public final static String DEFAULT_SAVE_FILE_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "MVPDemoApplication"
            + File.separator
            + "download"
            + File.separator;
    public static String ACCOUNT="";
    public static String PASSWORD="";
    /*AES加密解密*/
    public static final String AESKEY="9ozLook&KIFzNVI6";
    public static final String AESVALUE="5St&cDfZEXW2Gzjb";

}
