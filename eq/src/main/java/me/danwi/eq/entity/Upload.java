package me.danwi.eq.entity;

/**
 * Created with Android Studio.
 * User: 最帅最帅的RunningSnail
 * Date: 16/7/27
 * Time: 下午4:35
 * <p/>
 * 上传参数
 */
public class Upload {
    //key
    public String key;
    //文件名
    public String fileName;
    //文件路径
    public String filePath;
    //非文件上传
    public String value;

    public Upload(Builder builder) {
        this.key = builder.key;
        this.fileName = builder.fileName;
        this.filePath = builder.filePath;
        this.value = builder.value;
    }

    public static class Builder {
        //key
        private String key;
        //文件名
        private String fileName;
        //文件路径
        private String filePath;
        //非文件上传
        private String value;

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Upload build() {
            return new Upload(this);
        }
    }

    @Override
    public String toString() {
        return "Upload{" +
                "key='" + key + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
