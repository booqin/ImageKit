package com.boqin.image_kit.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * TODO
 * Created by Boqin on 2017/4/7.
 * Modified by Boqin
 *
 * @Version
 */
public class FileUtil {
    public FileUtil() {
    }

    public static String getSysDiskCachePath(Context context) {
        if(context == null) {
            return null;
        } else {
            String cachePath;
            if(!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
                cachePath = context.getCacheDir().getPath();
            } else {
                cachePath = context.getExternalCacheDir().getPath();
            }

            return cachePath;
        }
    }

    public static boolean deleteDirectory(String sPath) {
        if(TextUtils.isEmpty(sPath)) {
            return true;
        } else {
            if(!sPath.endsWith(File.separator)) {
                sPath = sPath + File.separator;
            }

            File dirFile = new File(sPath);
            if(dirFile.exists() && dirFile.isDirectory()) {
                boolean flag = true;
                File[] files = dirFile.listFiles();
                File[] arr$ = files;
                int len$ = files.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    File file = arr$[i$];
                    if(file.isFile()) {
                        flag = deleteFile(file.getAbsolutePath());
                        if(!flag) {
                            break;
                        }
                    } else {
                        flag = deleteDirectory(file.getAbsolutePath());
                        if(!flag) {
                            break;
                        }
                    }
                }

                return !flag?false:dirFile.delete();
            } else {
                return false;
            }
        }
    }

    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if(file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }

        return flag;
    }

    public static boolean copyFile(String oldPath, String newPath) {
        boolean result = false;
        if(!TextUtils.isEmpty(oldPath) && !TextUtils.isEmpty(newPath)) {
            FileInputStream inStream = null;
            FileOutputStream fs = null;

            try {
                boolean e = false;
                File oldfile = new File(oldPath);
                if(oldfile.exists()) {
                    inStream = new FileInputStream(oldPath);
                    fs = new FileOutputStream(newPath);
                    byte[] buffer = new byte[1024];

                    int e1;
                    while((e1 = inStream.read(buffer)) != -1) {
                        fs.write(buffer, 0, e1);
                    }

                    inStream.close();
                    result = true;
                }
            } catch (Exception var20) {
                var20.printStackTrace();
            } finally {
                if(inStream != null) {
                    try {
                        inStream.close();
                        inStream = null;
                    } catch (IOException var19) {
                        var19.printStackTrace();
                    }
                }

                if(fs != null) {
                    try {
                        fs.close();
                        fs = null;
                    } catch (IOException var18) {
                        var18.printStackTrace();
                    }
                }

            }
        }

        return result;
    }

    public static boolean delAllFiles(String path) {
        File file = new File(path);
        if(file.exists()) {
            path = file.getPath();
            if(path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }

            String tmp_path = path + "_tmp";

            try {
                Runtime.getRuntime().exec(new String[]{"mv", path, tmp_path}).waitFor();
                Runtime.getRuntime().exec(new String[]{"rm", "-r", tmp_path});
            } catch (Exception var4) {
                var4.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean checkMD5(InputStream is, String verfyMD5) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            byte[] data = new byte[4096];
            boolean length = false;

            int length1;
            while((length1 = is.read(data)) > 0) {
                e.update(data, 0, length1);
            }

            is.close();
            BigInteger number = new BigInteger(1, e.digest());

            String md5;
            for(md5 = number.toString(16); md5.length() < 32; md5 = "0" + md5) {
                ;
            }

            return verfyMD5.equals(md5);
        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
    }

    public static long getFileSize(File file) {
        if(file != null && file.exists()) {
            if(!file.isDirectory()) {
                return file.length();
            } else {
                long size = 0L;
                File[] children = file.listFiles();

                for(int i = 0; i < children.length; ++i) {
                    size += getFileSize(children[i]);
                }

                return size;
            }
        } else {
            return 0L;
        }
    }
}

