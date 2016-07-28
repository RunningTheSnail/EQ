package me.danwi.eq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.danwi.eq.entity.Upload;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by RunningSnail on 16/7/27.
 * 文件上传辅助类
 */
public class EQUtils {

    private EQUtils() {

    }

    public static Map<String, RequestBody> combine(List<Upload> uploadList) {
        Map<String, RequestBody> params = new HashMap<>();

        if (uploadList != null) {
            for (Upload upload : uploadList) {
                RequestBody requestBody;
                if (upload.value != null) {
                    requestBody = RequestBody.create(null, upload.value);
                    params.put(upload.key, requestBody);
                } else {
                    requestBody = RequestBody.create(MultipartBody.FORM, new File(upload.filePath));
                    params.put(upload.key + "\";fileName=" + upload.fileName, requestBody);
                }
            }
        }
        return params;
    }
}
