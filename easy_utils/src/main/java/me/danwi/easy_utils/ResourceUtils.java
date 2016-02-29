package me.danwi.easy_utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by tanshuai on 16/2/27.
 */
public class ResourceUtils {

    private ResourceUtils() {

    }

    /**
     * 从Assets资源中获取数据
     *
     * @param inputStream:文件读取流
     * @return
     */
    private static String getData(InputStream inputStream) {

        StringBuilder total = new StringBuilder();
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return total.toString();
    }

    /**
     * 从Assets资源中读取数据
     *
     * @param fileName:文件名
     * @return
     */
    public static String getDataFromAssets(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }

        InputStream inputStream;
        try {
            inputStream = AppContext.getContext().getAssets().open(fileName);
            return getData(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Raw资源中读取数据
     *
     * @param resId:资源id
     * @return
     */
    public static String getDataFromRaw(int resId) {
        InputStream inputStream = AppContext.getContext().getResources().openRawResource(resId);
        return getData(inputStream);
    }
}
