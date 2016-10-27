package me.danwi.eq.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

import me.danwi.eq.EQApplication;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/8/10
 * Time: 下午4:12
 */
public class ImageUtils {

    /**
     * 计算缩放比例
     *
     * @param options   从中分析出width,height
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //获取源图片的高度和宽度
        int width = options.outWidth;
        int height = options.outHeight;
        //压缩比例
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round((float) height / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);
            //选择最小比例,保证压缩后的高宽和目标相等或者高于
            inSampleSize = heightRatio > widthRatio ? widthRatio : heightRatio;
        }
        return inSampleSize;
    }

    /**
     * 按比例压缩图片
     *
     * @param path       图片保存的路径
     * @param data       字节中获取
     * @param resourceId 从项目中获取
     * @param reqWidth   目标宽度
     * @param reqHeight  目标高度
     * @return
     */
    private static Bitmap decode(String path, byte[] data, int resourceId, int reqWidth, int reqHeight) {
        Bitmap bitmap = null;
        //第一次解析将inJustDecodeBounds设置为true,获取图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (path != null) {
            BitmapFactory.decodeFile(path, options);
        }
        if (data != null) {
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        if (resourceId != 0) {
            BitmapFactory.decodeResource(EQApplication.getContext().getResources(), resourceId, options);
        }
        //计算缩放比例
        int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        //再次解析图片
        if (path != null) {
            bitmap = BitmapFactory.decodeFile(path, options);
        }
        if (data != null) {
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        if (resourceId != 0) {
            bitmap = BitmapFactory.decodeResource(EQApplication.getContext().getResources(), resourceId, options);
        }
        return bitmap;
    }


    public static Bitmap decodeSd(String path, int reqWidth, int reqHeight) {
        return decode(path, null, 0, reqWidth, reqHeight);
    }

    public static Bitmap decodeByteData(byte[] data, int reqWidth, int reqHeight) {
        return decode(null, data, 0, reqWidth, reqHeight);
    }

    public static Bitmap decodeResource(int resourceId, int reqWidth, int reqHeight) {
        return decode(null, null, resourceId, reqWidth, reqHeight);
    }

    /**
     * 将bitmap转成字节数组
     *
     * @param bitmap bitmap
     * @return
     */
    public static byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //质量压缩
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
