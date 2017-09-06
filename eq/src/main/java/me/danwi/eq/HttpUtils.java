package me.danwi.eq;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import me.danwi.eq.entity.Param;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/7/27
 * Time: 下午4:04
 * <p>
 * 文件上传辅助类,避免麻烦的参数拼接
 */
public class HttpUtils {

    private HttpUtils() {

    }

    /**
     * 单个或多个文件上传,可以携带其他参数
     * 当value和filePath都包含值时,优先级:非文本(普通键值对),本地文件,字节数组
     *
     * @param paramList
     * @return
     */
    public static Map<String, RequestBody> combine(Param... paramList) {
        Map<String, RequestBody> params = new HashMap<>();
        if (paramList != null) {
            for (Param param : paramList) {
                RequestBody requestBody;
                if (param.value != null) {
                    requestBody = RequestBody.create(null, param.value);
                    //普通键值对添加
                    params.put(param.key, requestBody);
                } else {
                    if (param.filePath != null) {
                        requestBody = RequestBody.create(MediaType.parse(param.mediaType), new File(param.filePath));
                    } else {
                        requestBody = RequestBody.create(MediaType.parse(param.mediaType), param.bytes);
                    }
                    //包含文件
                    params.put(param.key + "\"; filename=\"" + param.fileName, requestBody);
                }
            }
        }
        return params;
    }

    /**
     * 获取文件的MimeType
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getMimeType(Context context, Uri uri) {
        String mimeType = null;
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            ContentResolver cr = context.getContentResolver();
            mimeType = cr.getType(uri);
        } else {
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                    .toString());
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                    fileExtension.toLowerCase());
        }
        return mimeType;
    }

}
