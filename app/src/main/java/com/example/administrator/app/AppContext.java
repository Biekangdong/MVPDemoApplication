package com.example.administrator.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.module.entity.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class AppContext {
    private static final String saveData = "SAVE_DATA";
    private static AppContext appContext;
    private Context context;

    public static AppContext getInstance(Context context) {
        if (appContext == null) {
            appContext = new AppContext();
            appContext.context = context;
        }
        return appContext;
    }

    //SharedPreferences存储
    public static void setPreferences(Context context, String key, String values) {
        SharedPreferences preferences = context.getSharedPreferences(saveData, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, values);
        editor.commit();
    }

    //SharedPreferences获取
    public static String getPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(saveData, Context.MODE_PRIVATE);
        String values = preferences.getString(key, "");
        return values;
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    /**
     * 清除缓存目录
     *
     * @param dir     目录
     * @param curTime 当前系统时间
     * @return
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }




    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            // 把config建在files目录下
            // fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            // 把config建在(自定义)app_config的目录下
            File dirConf = context.getDir(AppConfig.APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, AppConfig.APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void setProperties(Properties ps) {
        Properties props = getProps();
        props.putAll(ps);
        setProperties(props);
    }

    public void setProperty(String key, String value) {
        Properties props = getProps();
        props.setProperty(key, value);
        setProps(props);
    }

    public String getProperty(String key) {
        Properties props = getProps();
        return (props != null) ? props.getProperty(key) : null;
    }

    public Properties getProps() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取files目录下的config
            // fis = activity.openFileInput(APP_CONFIG);

            // 读取app_config目录下的config
            File dirConf = context.getDir(AppConfig.APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator
                    + AppConfig.APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }
    public void removeProperty(String... key) {
        Properties props = getProps();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }



    /**
     * 保存登录用户的信息
     *
     * @param user
     */
    @SuppressWarnings("serial")
    public void saveUserInfo(final UserInfo user) {
        if (null == user) {
            return;
        }
        setProperties(new Properties() {
            {
//                setProperty("user._id", String.valueOf(user.get_id()));
//                setProperty("user.phone", String.valueOf(user.getPhone()));
//                setProperty("user.parent_id", String.valueOf(user.getParent_id()));
//                setProperty("user.password", String.valueOf(user.getPassword()));
//                setProperty("user.date", String.valueOf(user.getDate()));
//                setProperty("user.time", String.valueOf(user.getTime()));
//                setProperty("user.is_partner", String.valueOf(user.getIs_partner()));
//                setProperty("user.photo", String.valueOf(user.getPhoto()));
//                setProperty("user.province_id", String.valueOf(user.getParent_id()));
//                setProperty("user.city_id", String.valueOf(user.getCity_id()));
//                setProperty("user.province", String.valueOf(user.getProvince()));
//                setProperty("user.city", String.valueOf(user.getCity()));
            }
        });
    }
    /**
     * 获取登录信息
     *
     * @return
     */
//    public UserInfo getUserInfo() {
//        UserInfo user = new UserInfo();
//        user.set_id(getProperty("user._id"));
//        user.setPhone(getProperty("user.phone"));
//        user.setParent_id(getProperty("user.parent_id"));
//        user.setPassword(getProperty("user.password"));
//        user.setDate(getProperty("user.date"));
//        user.setTime(getProperty("user.time"));
//        user.setIs_partner(getProperty("user.is_partner"));
//        user.setPhoto(getProperty("user.photo"));
//        user.setParent_id(getProperty("user.province_id"));
//        user.setCity_id(getProperty("user.city_id"));
//        user.setProvince(getProperty("user.province"));
//        user.setCity(getProperty("user.city"));
//        return user;
//    }

    /**
     * 清除登录信息注销登录
     */
    public void cleanUserInfo() {
        removeProperty("user._id", "user.phone", "user.parent_id",
                "user.password", "user.date", "user.time", "user.is_partner", "user.photo",
                "user.province_id","user.city_id","user.province","user.city");
    }
    /**
     * 用户注销
     */
    public void logout() {
        // 清除已登录用户的信息
        cleanUserInfo();
    }
}
