package me.danwi.eq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.danwi.eq.entity.Upload;
import okhttp3.MultipartBody;

/**
 * Created by RunningSnail on 16/7/27.
 */
public class EQUtils {
    public static Map<String, MultipartBody.Part> combine(List<Upload> uploadList) {
        Map<String, MultipartBody.Part> params = new HashMap<>();

        if (uploadList != null) {
            for (Upload upload : uploadList) {
                MultipartBody.Part part = null;
                if (upload.value != null) {
                    part = MultipartBody.Part.createFormData(upload.key, upload.value);
                }
//                MultipartBody.Part part1 = new MultipartBody.Builder().addFormDataPart(upload.file, upload.fileName, RequestBody.create(MultipartBody.FORM, new File(upload.filePath))).build();
//                params.put(upload.file, requestBody);
            }
        }
        return params;
    }
}
